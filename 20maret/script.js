$(".btn").onclick = scrape();

function scrape() {
    const url = document.getElementById("url").value.split("?");
    console.log(url);
    $.ajax({
        url: url[0] + "?__a=1",
        type: 'get',
        success: function (post) {
            console.log(post);
            image = post.graphql.shortcode_media.display_url;
            // window.open(url_image);
            image_html = '<img src="' + image + '"style="width:200px">';
            $(".image").append(image_html);
        }
    });
}