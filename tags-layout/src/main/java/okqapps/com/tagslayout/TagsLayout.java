package okqapps.com.tagslayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TagsLayout extends FlowLayout {
    private List<TagItem> tagItemList;
    private ArrayList<Object> selectedTags = new ArrayList<>();
    private Context context;
    private int maxSelectedNumber = -1;
    private String maxNumberMessage = null;
    private TagTextSize tagTextSize = TagTextSize.MEDIUM;
    private UnSelectedTagTheme unSelectedTagTheme = UnSelectedTagTheme.LIGHT;
    private int horizontalMargin = 5;
    private int verticalMargin = 5;
    private int horizontalPadding = 10;
    private int verticalPadding = 10;
    private boolean viewMode;

    public TagsLayout(Context context) {
        super(context);
    }

    public TagsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
//        return Color.argb(a,
//                Math.min(r, 255),
//                Math.min(g, 255),
//                Math.min(b, 255));
        return Color.argb(a,
                Math.max(r, 0),
                Math.max(g, 0),
                Math.max(b, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * initialize and draw tags on screen this method should be called after finish setup view with all items and and properties
     *
     * @param context
     * @param tagItemList
     */
    public void initializeTags(Context context, List<TagItem> tagItemList) {
        this.context = context;
        this.tagItemList = tagItemList;
        drawTags();
    }

    private void drawTags() {
        selectedTags = new ArrayList<>();
        for (final TagItem tagItem :
                tagItemList) {
            final TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tag_layout, null);
            if (tagTextSize == TagTextSize.SMALL) {
                textView.setTextAppearance(context, android.R.style.TextAppearance_Small);
            } else if (tagTextSize == TagTextSize.MEDIUM) {
                textView.setTextAppearance(context, android.R.style.TextAppearance_Medium);
            } else if (tagTextSize == TagTextSize.LARGE) {
                textView.setTextAppearance(context, android.R.style.TextAppearance_Large);
            }
            textView.setText(tagItem.getTagText());

            if (tagItem.isSelected()) {
                selectItem(textView, tagItem);
                selectedTags.add(tagItem.getTagID());
            } else {
                setTextViewUnSelectedBackground(textView);
            }
//            else {
//                textView.setBackgroundResource(R.drawable.textview_tag_background_gray);
//                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGray1));
//            }
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //convert pixels to dp
            int horizontalMarginInPx = (int) (horizontalMargin * Resources.getSystem().getDisplayMetrics().density);
            int verticalMarginInPx = (int) (verticalMargin * Resources.getSystem().getDisplayMetrics().density);
            buttonLayoutParams.setMargins(horizontalMarginInPx, verticalMarginInPx, horizontalMarginInPx, verticalMarginInPx);
            buttonLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(buttonLayoutParams);
            textView.setGravity(Gravity.CENTER);
            //convert do to pixels
            int horizontalPaddingInPx = (int) (horizontalPadding * Resources.getSystem().getDisplayMetrics().density);
            int verticalPaddingInPx = (int) (verticalPadding * Resources.getSystem().getDisplayMetrics().density);
            textView.setPadding(horizontalPaddingInPx, verticalPaddingInPx, horizontalPaddingInPx, verticalPaddingInPx);
            if (!viewMode) {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //tag selected
                        if (!getSelectedTags().contains(tagItem.getTagID())) {
                            if (getSelectedTags().size() == maxSelectedNumber) {
                                if (maxNumberMessage != null) {
                                    Utili.getInstance().showToastMessage(context, maxNumberMessage);
                                }
                                return;
                            }
                            getSelectedTags().add(tagItem.getTagID());
                            selectItem(textView, tagItem);
                        }//tag unselected
                        else {
                            getSelectedTags().remove(tagItem.getTagID());
                            setTextViewUnSelectedBackground(textView);
                        }
                    }
                });
            }
            addView(textView);
        }
    }

    private void selectItem(TextView textView, TagItem tagItem) {
        try {
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            int radius = (int) (20 * Resources.getSystem().getDisplayMetrics().density);
            shape.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
            shape.setColor(Color.parseColor(tagItem.getSelectedBackgroundColor()));
//                        shape.setStroke(3, borderColor);
            if (Build.VERSION.SDK_INT >= 16) {
                textView.setBackground(shape);
            } else {
                textView.setBackgroundDrawable(shape);
            }
            int darkerColer;
            if (tagItem.getSelectedTextColor() == null) {
                darkerColer = manipulateColor(Color.parseColor(tagItem.getSelectedBackgroundColor()), 0.3f);
            } else {
                darkerColer = Color.parseColor(tagItem.getSelectedTextColor());
            }
            textView.setTextColor(darkerColer);
            shape.setStroke(1, darkerColer);

        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void setTextViewUnSelectedBackground(TextView textView) {
        if (unSelectedTagTheme == UnSelectedTagTheme.LIGHT) {
            if (Build.VERSION.SDK_INT >= 16) {
                textView.setBackground(getResources().getDrawable(R.drawable.textview_tag_background_light));
            } else {
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_tag_background_light));
            }
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGray1));
        } else if (unSelectedTagTheme == UnSelectedTagTheme.DARK) {
            if (Build.VERSION.SDK_INT >= 16) {
                textView.setBackground(getResources().getDrawable(R.drawable.textview_tag_background_dark));
            } else {
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_tag_background_dark));
            }
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhiteBackground));
        } else {
            if (Build.VERSION.SDK_INT >= 16) {
                textView.setBackground(getResources().getDrawable(R.drawable.textview_tag_background_gray));
            } else {
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_tag_background_gray));
            }
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGray2));
        }
    }

    /**
     * Set limit for number of tags that user can select
     *
     * @param maxSelectedNumber
     */
    public void setMaxSelectedNumber(int maxSelectedNumber) {
        this.maxSelectedNumber = maxSelectedNumber;
    }

    /**
     * set message that will be shown to user when reach the max selected number
     *
     * @param maxNumberMessage
     */
    public void setMaxNumberMessage(String maxNumberMessage) {
        this.maxNumberMessage = maxNumberMessage;
    }

    /**
     * Specify the text size (Small, medium or Large)
     *
     * @param tagTextSize
     */
    public void setTagTextSize(TagTextSize tagTextSize) {
        this.tagTextSize = tagTextSize;
    }

    /**
     * get list of IDs for selected tags
     *
     * @return
     */
    public ArrayList<Object> getSelectedTags() {
        return selectedTags;
    }

    /**
     * Specify Theme for tag when tage item isn't selected (dark, light or gray)
     *
     * @param unSelectedTagTheme
     */
    public void setUnSelectedTagTheme(UnSelectedTagTheme unSelectedTagTheme) {
        this.unSelectedTagTheme = unSelectedTagTheme;
    }

    /**
     * value of horizontal margin between views
     *
     * @param horizontalMargin
     */
    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    /**
     * value of vertical margin between views
     *
     * @param verticalMargin
     */
    public void setVerticalMargin(int verticalMargin) {
        this.verticalMargin = verticalMargin;
    }

    /**
     * value of horizontal padding for view
     *
     * @param horizontalPadding
     */
    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }

    /**
     * value of vertical padding for view
     *
     * @param verticalPadding
     */
    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }

    public void setViewMode(boolean viewMode) {
        this.viewMode = viewMode;
    }
}
