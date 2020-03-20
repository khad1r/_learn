document.querySelector(".search").addEventListener("click", (e) => {
    const link = document.getElementById("link").value.split("/");
    id = link[4];
    console.log(id);

    var request = new XMLHttpRequest();
    request.open('GET', "https://www.instagram.com/p/" + id + "/?__a=1", true);

    request.onload = function () {
        if (this.status >= 200 && this.status < 400) {
            // Success!
            var resp = JSON.parse(this.response);
            if ('edge_sidecar_to_children' in resp.graphql.shortcode_media) {
                Multiple(resp);
            } else {
                Single(resp)
            }
        } else {
            // We reached our target server, but it returned an error
            alert("Link salah atau Internet lo Lemot");

        }
    };

    request.onerror = function () {
        // There was a connection error of some sort
        alert("Paketan lo habis");
    };

    request.send();
});

function Multiple(post) {
    path = post.graphql.shortcode_media;
    download = feed(path);
    path = post.graphql.shortcode_media.edge_sidecar_to_children.edges;
    for (var i = 1; i < path.length; i++) {
        pathChild = path[i].node;
        feed(pathChild)
    }
}

function Single(post) {
    path = post.graphql.shortcode_media;
    download = feed(path);
}

function feed(path) {
    console.log(path);
    if (path.is_video) {
        window.open(path.video_url, '_blank');
    } else {
        window.open(path.display_url, '_blank');
    }
    return html;
}