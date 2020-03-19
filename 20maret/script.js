function scrape() {
    const link = document.getElementById("link").value.split("/");
    console.log(link);
    url = "https://www.instagram.com/p/" + link[4] + "/?__a=1";
    console.log(url);
    $.ajax({
        url: url,
        type: 'get',
        success: function (post) {
            console.log(post);
            image = post.graphql.shortcode_media.display_url;
            // window.open(url_image);
            image_html = '<img src="' + image + '"style="width:200px">';
            $(".scrape").append(image_html);
            return;
        }
    });
    url = "https://www.instagram.com/" + link[4] + "/?__a=1";
    console.log(url);
    $.ajax({
        url: url,
        type: 'get',
        success: function (story) {
            console.log(story);
            return;
        }
    });
}