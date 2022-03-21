package com.loasan.lucky

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundedImageIcon @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {
    private var width = 0f
    private var height = 0f
    private val defaultRadius = 0
    private var radius = 0
    private var leftTopRadius = 0
    private var rightTopRadius = 0
    private var rightBottomRadius = 0
    private var leftBottomRadius = 0

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
        val array = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageIcon)
        radius = array.getDimensionPixelOffset(R.styleable.RoundedImageIcon_radius, defaultRadius)
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.RoundedImageIcon_left_top_radius, defaultRadius)
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.RoundedImageIcon_right_top_radius, defaultRadius)
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.RoundedImageIcon_right_bottom_radius, defaultRadius)
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.RoundedImageIcon_left_bottom_radius, defaultRadius)
        if (defaultRadius == leftTopRadius) {
            leftTopRadius = radius
        }
        if (defaultRadius == rightTopRadius) {
            rightTopRadius = radius
        }
        if (defaultRadius == rightBottomRadius) {
            rightBottomRadius = radius
        }
        if (defaultRadius == leftBottomRadius) {
            leftBottomRadius = radius
        }
        array.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        width = getWidth().toFloat()
        height = getHeight().toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        //这里做下判断，只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        val maxLeft = Math.max(leftTopRadius, leftBottomRadius)
        val maxRight = Math.max(rightTopRadius, rightBottomRadius)
        val minWidth = maxLeft + maxRight
        val maxTop = Math.max(leftTopRadius, rightTopRadius)
        val maxBottom = Math.max(leftBottomRadius, rightBottomRadius)
        val minHeight = maxTop + maxBottom
        if (width >= minWidth && height > minHeight) {
            val path = Path()
            //四个角：右上，右下，左下，左上
            path.moveTo(leftTopRadius.toFloat(), 0f)
            path.lineTo(width - rightTopRadius, 0f)
            path.quadTo(width, 0f, width, rightTopRadius.toFloat())
            path.lineTo(width, (height - rightBottomRadius).toFloat())
            path.quadTo(width, height.toFloat(), width - rightBottomRadius, height.toFloat())
            path.lineTo(leftBottomRadius.toFloat(), height.toFloat())
            path.quadTo(0f, height.toFloat(), 0f, (height - leftBottomRadius).toFloat())
            path.lineTo(0f, leftTopRadius.toFloat())
            path.quadTo(0f, 0f, leftTopRadius.toFloat(), 0f)
            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }
}