/**
 * 
 */

var afbeeldingToevoegen = function () {
    for (var i = 0, len = fotos.length; i < len; i++) {
        var html = '<li>';
        var src = 'images/' + fotos[i] + '.jpg';
        console.log(src);
        html += '<img alt="' + fotos[i] + '"  title="' + fotos[i] + '" src="' + src + '" />';
        html += '</li>';
        $(".carousel ul").append(html);
    }

    $('.carousel li:first').show();
};

var slide = function () {
    timer = setInterval(volgendeAfbeelding, 20000);
};

var volgendeAfbeelding = function () {
    if (huidigeAfbeelding < fotos.length) {
        huidigeAfbeelding++;
        $('.carousel li:nth-child(' + (huidigeAfbeelding - 1) + ')').fadeOut(function () {
            $('.carousel li:nth-child(' + huidigeAfbeelding + ')').fadeIn();
        });
        if (huidigeAfbeelding == fotos.length) {
            huidigeAfbeelding = 1;
        }
    }
    console.log(huidigeAfbeelding)
};


var start = function () {
    afbeeldingToevoegen();
    slide();
};
$(document).ready(start);