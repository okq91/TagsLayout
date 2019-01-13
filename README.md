# TagsLayout
Android library to draw tags with colors and themes 

![selected tags](https://user-images.githubusercontent.com/11648787/49321174-82f14f80-f50e-11e8-9f69-8e82d31fd8a9.png)       


## How do I use TagsLayout?
You can simply use it in your project by adding the below dependency to your app gradle:
```
dependencies {

implementation 'com.okqapps.tagslayout:tags-layout:1.0.2'

}
```
And then you can add tagslayout view to your activity or fragment xml

```
   <okqapps.com.tagslayout.TagsLayout
        android:id="@+id/tagsLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:layout_margin="5dp"
        android:gravity="left" />
```

In your class you can create your tages as you need

        TagsLayout tagsLayout = findViewById(R.id.tagsLayout);
        List<TagItem> tagItems = new ArrayList<>();
        tagItems.add(new TagItem(1, "Health", "#000000", "#FFFFFF", false));//you can use this constructor to set text and background color
        tagItems.add(new TagItem(2, "Sport", "#6ec175", true));//you can use this constructor to set background color without set text color
        tagItems.add(new TagItem(3, "Science", "#DFF2FF", false));
        tagItems.add(new TagItem(4, "Technology", "#ecf44b", false));
        tagItems.add(new TagItem(5, "Art", "#FBE7D5", false));
        tagItems.add(new TagItem(6, "Physics", "#FBE7D5", "#8B572A", false));
        //call initialize after set all proprieties 
        tagsLayout.initializeTags(this, tagItems);
        
        
###### You can set text size Samll, meduim or large        

tagsLayout.setTagTextSize(TagTextSize.SMALL);

###### Set tags theme Light, Dark or Gray

tagsLayout.setUnSelectedTagTheme(UnSelectedTagTheme.GRAY);

![Dark mode](https://user-images.githubusercontent.com/11648787/49321178-8b498a80-f50e-11e8-964d-847009ceeffe.png)       


![Light mode](https://user-images.githubusercontent.com/11648787/49321179-8b498a80-f50e-11e8-96c8-4383968c88aa.png)

###### You can set max number of tags that user can select      

tagsLayout.setMaxSelectedNumber(5);


###### You can set message to the user when it reaches the allowed max number

tagsLayout.setMaxNumberMessage("You can't choose mor than 5 tags");


###### Change default values for horizantal and vertical margin between tags in dp

tagsLayout.setHorizontalMargin(5);

tagsLayout.setVerticalMargin(5);


###### Change default values for horizantal and vertical padding for tags in dp

tagsLayout.setHorizontalPadding(5);

tagsLayout.setVerticalPadding(5);


###### For showing tags in view mode (user can't select)

tagsLayout.setViewMode(true);

###### Last thing you have to do is call 

tagsLayout.initializeTags(this, tagItems);

###### You can easily get list of selected tags' ids

tagsLayout.getSelectedTags()
