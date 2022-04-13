package com.loasan.lucky.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.loasan.lucky.R

private const val TAG = "Loasan:SuperEditText"

class SuperEditText : AppCompatEditText {

    constructor(context: Context):super(context){
        Log.d(TAG, "constructor 01: ")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        Log.d(TAG, "constructor 02: ")
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        Log.d(TAG, "constructor 03: ")
        init(context, attrs)
    }

    lateinit var mPaint: Paint
    var deleteIcon: Drawable? = null
    var deleteIconResID: Int = R.drawable.delete
    var deleteX: Int = 0
    var deleteY: Int = 0
    var deleteWidth: Int = 60
    var deleteHeight: Int = 60
    var clickedLeftIconResID: Int = R.drawable.ic_left_click
    var unClickedLeftIconResID: Int = R.drawable.ic_left_unclick
    private var clickedLeftIcon: Drawable? =null
    private var unClickedLeftIcon: Drawable? =null
    var leftX: Int = -1
    var leftY: Int = -1
    var leftWidth: Int = -1
    var leftHeight: Int = -1

    //光标
    private var cursor: Int = -1
    private var lineColorClick: Int = -1
    private var lineColorUnClick: Int = -1
    private var mColor: Int = -1
    private var linePosition: Int = -1




    //初始化属性
    @SuppressLint("Recycle")
    private fun init(context: Context, attrs: AttributeSet) {
        Log.d(TAG, "init: *********************")
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperEditText)
        clickedLeftIconResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_click,
            R.drawable.ic_left_click)
        clickedLeftIcon = ContextCompat.getDrawable(context, clickedLeftIconResID)!!

        unClickedLeftIconResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_unclick,
            R.drawable.ic_left_unclick)
        unClickedLeftIcon = ContextCompat.getDrawable(context, unClickedLeftIconResID)!!

        leftX = typedArray.getInteger(R.styleable.SuperEditText_left_x, 0)
        leftY = typedArray.getInteger(R.styleable.SuperEditText_left_y, 0)
        leftWidth = typedArray.getInteger(R.styleable.SuperEditText_left_width, 60)
        leftHeight = typedArray.getInteger(R.styleable.SuperEditText_left_height, 60)

        clickedLeftIcon!!.setBounds(leftX, leftY, leftWidth, leftHeight)
        unClickedLeftIcon!!.setBounds(leftX, leftY, leftWidth, leftHeight)
        // Drawable.setBounds(x,y,width,height) = 设置Drawable的初始位置、宽和高等信息
        // x = 组件在容器X轴上的起点、y = 组件在容器Y轴上的起点、width=组件的长度、height = 组件的高度

        // b. 未点击状态的左侧图标
        // 1. 获取资源ID
        /**
         * 初始化删除图标
         */
        //获取资源id
        deleteIconResID =
            typedArray.getResourceId(R.styleable.SuperEditText_ic_delete, R.drawable.delete)
        deleteIcon = ContextCompat.getDrawable(context,deleteIconResID)!!

        //3.设置图标大小
        // 起点(x，y)、宽= left_width、高 = left_height
        deleteX = typedArray.getInteger(R.styleable.SuperEditText_delete_x, 0)
        deleteY = typedArray.getInteger(R.styleable.SuperEditText_delete_y, 0)
        deleteWidth = typedArray.getInteger(R.styleable.SuperEditText_delete_width, 60)
        deleteHeight = typedArray.getInteger(R.styleable.SuperEditText_delete_height, 60)
        deleteIcon!!.setBounds(deleteX, deleteY, deleteWidth, deleteHeight)

        /**
         * 设置EditText左侧 & 右侧的图片（初始状态仅有左侧图片））
         */
        setCompoundDrawables(clickedLeftIcon, null, null, null)
        // setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom)介绍
        // 作用：在EditText上、下、左、右设置图标（相当于android:drawableLeft=""  android:drawableRight=""）
        // 备注：传入的Drawable对象必须已经setBounds(x,y,width,height)，即必须设置过初始位置、宽和高等信息
        // x:组件在容器X轴上的起点 y:组件在容器Y轴上的起点 width:组件的长度 height:组件的高度
        // 若不想在某个地方显示，则设置为null

        // 另外一个相似的方法：setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom)
        // 作用：在EditText上、下、左、右设置图标
        // 与setCompoundDrawables的区别：setCompoundDrawablesWithIntrinsicBounds（）传入的Drawable的宽高=固有宽高（自动通过getIntrinsicWidth（）& getIntrinsicHeight（）获取）
        // 不需要设置setBounds(x,y,width,height)


        /**
         * 初始化光标（颜色 & 粗细）
         */
        // 原理：通过 反射机制 动态设置光标
        // 1. 获取资源ID
        cursor = typedArray.getResourceId(R.styleable.SuperEditText_cursor, R.drawable.cursor)
        try {
            val reflectCursor =
                TextView::class.java.declaredFields.find { it.name == "mCursorDrawableRes" }.let {
                    it?.isAccessible = true
                    it?.set(this, mColor)
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        /**
         * 初始化分割线（颜色、粗细、位置）
         */
        //设置画笔
        mPaint = Paint()
        mPaint.strokeWidth = 6f

        // 2. 设置分割线颜色（使用十六进制代码，如#333、#8e8e8e）
        val lineColorClick_default =
            context.resources.getColor(R.color.lineColor_click) // 默认 = 蓝色#1296db
        val lineColorunClick_default =
            context.resources.getColor(R.color.lineColor_unclick) // 默认 = 灰色#9b9b9b
        lineColorClick =
            typedArray.getColor(R.styleable.SuperEditText_lineColor_click, lineColorClick_default)
        lineColorUnClick = typedArray.getColor(R.styleable.SuperEditText_lineColor_unclick,
            lineColorunClick_default)
        mColor = lineColorUnClick

        // 3. 分割线位置
        linePosition = typedArray.getInteger(R.styleable.SuperEditText_linePosition, 1)
        //4、 清除自带分割线
        background = null
    }

    override fun onTextChanged(
        text: CharSequence?, start: Int,
        lengthBefore: Int, lengthAfter: Int,
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        setDeleteIconVisible(hasFocus() && text?.length!! > 0, hasFocus())
        // hasFocus()返回是否获得EditTEXT的焦点，即是否选中
        // setDeleteIconVisible（） = 根据传入的是否选中 & 是否有输入来判断是否显示删除图标->>关注1
    }


    /**
     * 复写EditText本身的方法：onFocusChanged（）
     * 调用时刻：焦点发生变化时
     */
    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        setDeleteIconVisible(focused && length() > 0, focused)
        // focused = 是否获得焦点
        // 同样根据setDeleteIconVisible（）判断是否要显示删除图标->>关注1
    }

    /**
     * 关注1
     * 作用：判断是否显示删除图标 & 设置分割线颜色
     */
    private fun setDeleteIconVisible(deleteVisible: Boolean, leftVisible: Boolean) {
        setCompoundDrawables(if (leftVisible) clickedLeftIcon else unClickedLeftIcon, null,
            if (deleteVisible) deleteIcon else null, null)
        mColor = if (leftVisible) lineColorClick else lineColorUnClick
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                val drawable = deleteIcon
                if (event.x <= (width - paddingRight) && event.x >= width - paddingRight - drawable?.bounds?.width()!!) {
                    // 判断条件说明
                    // event.getX() ：抬起时的位置坐标
                    // getWidth()：控件的宽度
                    // getPaddingRight():删除图标图标右边缘至EditText控件右边缘的距离
                    // 即：getWidth() - getPaddingRight() = 删除图标的右边缘坐标 = X1
                    // getWidth() - getPaddingRight() - drawable.getBounds().width() = 删除图标左边缘的坐标 = X2
                    // 所以X1与X2之间的区域 = 删除图标的区域
                    // 当手指抬起的位置在删除图标的区域（X2=<event.getX() <=X1），即视为点击了删除图标 = 清空搜索框内容
                    setText("")
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = mColor
        setTextColor(mColor)
        // 绘制分割线
        // 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
        // 解决方案：线的长度 = 控件长度 + 延伸后的长度
        val x = this.scrollX//获取延申后的长度
        val w = this.measuredWidth

        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
        canvas?.drawLine(0f,
            (this.measuredHeight - linePosition).toFloat(),
            (w + x).toFloat(), (measuredHeight - linePosition).toFloat(), mPaint)
    }
}