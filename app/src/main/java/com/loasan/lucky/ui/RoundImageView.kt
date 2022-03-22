package com.loasan.lucky.ui


import android.graphics.drawable.BitmapDrawable

import android.os.Build


import android.content.Context

import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatImageView
import com.loasan.lucky.R


class RoundImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatImageView(context, attrs, defStyleAttr) {
    private var mWidth = 0
    private var mHeight = 0
    private var mRadius = 0f
    private var mPaint: Paint? = null
    lateinit var mBitmap: Bitmap
    private var mShader: BitmapShader? = null

    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1) {}

    private fun getAttributes(context: Context, attrs: AttributeSet?) {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView)
        mRadius = ta.getDimension(R.styleable.RoundImageView_radius, -1f)
        ta.recycle()
    }

    private fun initView(context: Context) {
        mPaint = Paint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas) {
        initPaint()
        if (mRadius < 0) {
            val radius = (Math.min(mWidth, mHeight) / 2).toFloat()
            mPaint?.let {
                canvas.drawCircle(
                    (mWidth / 2).toFloat(), (mHeight / 2).toFloat(), radius,
                    it
                )
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {    // 21及其以上
                mPaint?.let {
                    canvas.drawRoundRect(
                        0F, 0F, mWidth.toFloat(),
                        mHeight.toFloat(), mRadius, mRadius, it
                    )
                }
            } else {
                super.onDraw(canvas)
            }
        }

//        super.onDraw(canvas);
    }

    /**
     * 设置画笔
     */
    private fun initPaint() {
        initBitmap()
        initShader(mBitmap)
        mPaint?.shader = mShader
    }

    /**
     * 获取Bitmap
     */
    private fun initBitmap() {
        val drawable1 = drawable
        val drawable2 = background
        val drawable = drawable1 ?: drawable2 // 不能在构造方法中获取drawable，为null
        if (drawable is BitmapDrawable) {
            mBitmap = drawable.bitmap
        } else {
            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(mBitmap)
            drawable.draw(canvas)

            //            drawable.setBounds(0,0,width,height);
        }
    }

    /**
     * 获取BitmapShader
     */
    private fun initShader(bitmap: Bitmap?) {
        mShader = BitmapShader(bitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val sx = mWidth * 1.0f / bitmapWidth
        val sy = mHeight * 1.0f / bitmapHeight
        val scale = Math.max(sx, sy)
        val matrix = Matrix()
        matrix.setScale(scale, scale)
        mShader!!.setLocalMatrix(matrix)
    }

    init {
        getAttributes(context, attrs)
        initView(context)
    }
}