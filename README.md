# TagsLayout
Android library to draw tags with colors and themes 
## How do I use TagsLayout?
You can simply use it in your project by adding the below dependency to your app gradle:
```
dependencies {

implementation 'com.okqapps.tagslayout:tags-layout:1.0.1'

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
        
        

![dark mode](https://drive.google.com/file/d/1MFFBtYYTzkJheAnbk9BW8n_T2Itk5KzU/view?usp=sharing)       
