package okqapps.com.tagslayoutexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import okqapps.com.tagslayout.TagItem;
import okqapps.com.tagslayout.TagTextSize;
import okqapps.com.tagslayout.TagsLayout;
import okqapps.com.tagslayout.UnSelectedTagTheme;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TagsLayout tagsLayout = findViewById(R.id.tagsLayout);
        List<TagItem> tagItems = new ArrayList<>();
        tagItems.add(new TagItem(1, "Health", "#000000", "#FFFFFF", false));
        tagItems.add(new TagItem(2, "Sport", "#6ec175", true));
        tagItems.add(new TagItem(3, "Science", "#DFF2FF", false));
        tagItems.add(new TagItem(4, "Technology", "#ecf44b", false));
        tagItems.add(new TagItem(5, "Art", "#FBE7D5", false));
        tagItems.add(new TagItem(6, "Physics", "#FBE7D5", "#8B572A", false));
        tagsLayout.setTagTextSize(TagTextSize.SMALL);
        tagsLayout.setUnSelectedTagTheme(UnSelectedTagTheme.LIGHT);
        tagsLayout.setMaxSelectedNumber(5);
        tagsLayout.setMaxNumberMessage("You can't choose mor than 5 tags");
        tagsLayout.initializeTags(this, tagItems);
    }
}
