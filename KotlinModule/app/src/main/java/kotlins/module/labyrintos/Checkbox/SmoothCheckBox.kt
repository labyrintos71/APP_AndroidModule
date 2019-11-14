package kotlins.module.labyrintos.Checkbox

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import kotlins.module.labyrintos.Extension.dpToPx
import kotlins.module.labyrintos.R

/**
 * Created by Labyrintos on 2019-11-14
 */
class SmoothCheckBox : View, Checkable {
    private val KEY_INSTANCE = "INSTANCESTATE"
    private val COLOR_TICK = Color.WHITE
    private val COLOR_UNCHECKED = Color.WHITE
    private val COLOR_CHECKED = Color.parseColor("#FB4846")
    private val COLOR_FLOOR_UNCHECKED = Color.parseColor("DFDFDF")

    private val DRAW_SIZE = 25.dpToPx(context)
    private val ANIM_DURATION = 300
    private val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = checkedColor
        }
    }
    private val tickPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            color = floorColor
        }
    }
    private val floorPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
        }
    }

    private var checkedColor = COLOR_CHECKED
    private var unCheckedColor = COLOR_UNCHECKED
    private var floorColor = COLOR_FLOOR_UNCHECKED
    private var floorUnCheckColor = COLOR_FLOOR_UNCHECKED
    private var isChecked
    private var tickDrawing
    private var listener
    private var animDuration = ANIM_DURATION
    private var strokeWidth = 0
    private var listener
    private var tickPath = Path()
    private var centerPoint = Point()
    private var tickPoint = arrayOf(Point(), Point(), Point())
    //    private Point[] mTickPoints;
    //    private Point mCenterPoint;
    //    private Path mTickPath;
    //
    //
    //    private float mLeftLineDistance, mRightLineDistance, mDrewDistance;
    //    private float mScaleVal = 1.0f, mFloorScale = 1.0f;
    //    private int mWidth, mAnimDuration, mStrokeWidth;

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrs(attrs)
    }

    fun getAttrs(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.SmoothCheckBox).apply {
            tickPaint.color = getColor(R.styleable.SmoothCheckBox_color_tick, COLOR_TICK)
            animDuration = getInt(R.styleable.SmoothCheckBox_duration, ANIM_DURATION)
            floorColor = getColor(
                R.styleable.SmoothCheckBox_color_unchecked_stroke,
                COLOR_FLOOR_UNCHECKED
            )
            checkedColor = getColor(R.styleable.SmoothCheckBox_color_checked, COLOR_CHECKED)
            unCheckedColor = getColor(R.styleable.SmoothCheckBox_color_unchecked, COLOR_UNCHECKED)
            strokeWidth = getDimensionPixelSize(R.styleable.SmoothCheckBox_stroke_width, 0)
            recycle()
        }
        floorUnCheckColor = floorColor
        //리스트뷰 안에 넣을경우 아래 없고 이벤트 처리해주는게 편함
        /*setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {0
                toggle();
                mTickDrawing = false;
                mDrewDistance = 0;
                if (isChecked()) {
                    startCheckedAnimation();
                } else {
                    startUnCheckedAnimation();
                }
            }
        });*/
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
    }



    override fun setChecked(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}