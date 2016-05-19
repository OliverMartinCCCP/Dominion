var fotos = ["dominionSlide1", "dominionSlide2", "dominionSlide3", "dominionSlide1"];
var otherCards=["back","trash"]
var treasureCards=["copper","silver","gold"];
var victoryCards =["curse","duchy","estate","province"];
var actionCards=["adventurer","bureaucrat","cellar","chancellor","chapel","councilroom","feast","festival","gardens","laboratory","library","market","militia","mine","moat","moneylender","remodel","smithy","spy","thief","throneroom","village","witch","woodcutter","workshop",];
var deck = ["copper","copper","estate","estate","estate","copper","copper","copper","copper","copper"]
var hand = [];
var huidigeAfbeelding = 1;
var timer;

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

var newGame = function(e){
	e.preventDefault();
	$(".startGame").hide();
	$(".newGame").show();
}


var initializatie = function(){
	var request = $.ajax({
		cache: false,
	    url: "/DominionServer/DominionServlet",
	    data: { operation: 'initialize', name1: $("#name1").val(), name2: $("#name2").val() }
	});

	request.done(function (data) {
		console.log(data);
	    $('#response').html("Dit is de eerste naam: " + data.name1 + ". En dit is de tweede naam: " + data.name2);
	});
	request.fail(function (jqXHR, textStatus) {
	    alert(jqXHR.status);
        alert(textStatus);
	});
	$(".newGame").hide();
	$(".play").show();

}

var shuffleDeck=function(){
	var i = 0, j = 0, temp = null
	for (i = deck.length - 1; i > 0; i--) {
	    j = Math.floor(Math.random() * (i + 1))
	    temp = deck[i]
    	deck[i] = deck[j]
    	deck[j] = temp
  }
}

var shuffleActionCards = function(){
	var i = 0, j = 0, temp = null
	for (i = actionCards.length - 1; i > 0; i--) {
	    j = Math.floor(Math.random() * (i + 1))
	    temp = actionCards[i]
	    actionCards[i] = actionCards[j]
	    actionCards[j] = temp
  }
}

var addAmounts = function(){
	/*
	 * victory amount = 8
	 * curse amount = 10
	 * treasure amount = 60copper - starthand
	 * 					 40 silver
	 * 					 30 gold
	 * kingdom amount = 10
	*/
	var value;
	for(i=1;i<4;i++){
		var title = $("#treasureCards div:nth-child("+i+")").attr("title");
		switch(title){
		case "gold":
			value = 30
			$("#gold span:first-child").html(value);
		case "silver":
			value = 40;
			$("#silver span:first-child").html(value);
		case "copper":
			value = 46;
			$("#copper span:first-child").html(value);
			
		}
	}
	value = 10
	$("#actionCards .amount").html(value);
	for(i=1;i<5;i++){
		var title = $("#victoryCards div:nth-child("+i+")").attr("title");
		value = 8
		$("#victoryCards .amount").html(value);
		if(title == "curse"){
			value = 10;
			$("#curse .amount").html(value);
		}
	}
	
	
}

var addActionCards = function(){

	var i = 0;
	for(i; i < 10; i++)
	{
		var image = "url(../media/" + actionCards[i] + ".jpg";
		i++;
		$("#actionCards div:nth-child(" + i + ")").css("background-image", image);
		$("#actionCards div:nth-child(" + i + ")").attr("title", actionCards[i-1]);
		i--;
		
	}
	
}

var addTreasureCards = function(){

	var i = 1;
	for(i; i<treasureCards.length+1; i++){
		var title = $("#treasureCards div:nth-child(" + i + ")").attr("title");
		var image = "url(../media/"+ title + ".jpg)";
		console.log(title);
		console.log(image);
		$("#treasureCards div:nth-child(" + i + ")").css("background-image", image);
	}
}

var addVictoryCards = function(){
	var i = 1;
	for(i; i<victoryCards.length+1; i++){
		var title = $("#victoryCards div:nth-child(" + i + ")").attr("title");
		var image = "url(../media/"+ title + ".jpg)";
		console.log(title);
		console.log(image);
		$("#victoryCards div:nth-child(" + i + ")").css("background-image", image);
	}
}

var makeStartHand = function(){
	i = 0
	for(i;i<5;i++){
		var title = deck[1];
		var html = "<div "+"class=" + title +" "+" title="+ title + ">";
		var image = "url(../media/"+title+".jpg)";
		html += "</div>";
		$("#hand").append(html);
		$("."+deck[1]).css("background-image", image);
		var cardToRemove = deck[1];
		deck.splice($.inArray(cardToRemove, deck),1);
		hand.push(title);
	}
}

var zoom = function(){
	$("#zoom").show();
	var image = "url(../media/"+ $(this).attr("title") + ".jpg)";
	$("#zoom").css("background-image", image);
}

var unzoom = function(){
	$("#zoom").hide();
}

var playAllTreasures = function(e){
	e.preventDefault();
	var i = hand.length+1;
	var value = 0
	for(i;i>0;i--){
		var title = $("#hand div:nth-child("+i+")").attr("title");
		var html = "<div "+"class=" + title +" "+" title="+ title + ">";
		var image = "url(../media/"+title+".jpg)";
		html += "</div>";
		if(title =="copper" || title =="silver" || title=="gold"){
			if(title =="copper"){
				value +=1;
			}
			else if(title=="silver"){
				value += 3;
			}
			else{
				value += 6;
			}
			$("#board").append(html);
			$("#board div").css("background-image",image);
			$("#hand div:nth-child("+i+")").remove();
		}
			
		
	}
	$("#coins").text(value)
	$("#playAllTreasures").hide()
}

var playTreasureCard = function(){
	var value = 0;
	var title = $(this).attr("title");
	console.log(title);
	for(i = 0; i < treasureCards.length;i++){
		if(title == treasureCards[i]){
			var html = "<div "+"class=" + title +" "+" title="+ title + ">";
			var image = "url(../media/"+title+".jpg)";
			html += "</div>";
			$("#board").append(html);
			$("#board div").css("background-image",image);
			$(this).remove();
		}
	}	
	value += 1
	$("#coins").text(value)
	$("#playAllTreasures").hide()
}


var start = function () {
    afbeeldingToevoegen();
    slide();
    shuffleDeck();
    shuffleActionCards();
    addAmounts();
    addActionCards();
    addTreasureCards();
    addVictoryCards();
    makeStartHand();
    $('.startGame').show();
    $("#newGame").on('click', newGame);
    $("#initialize").on('click', initializatie);
    $(".big div").on('mouseenter', zoom)
    $(".big div").on('mouseleave', unzoom)
    $("#playAllTreasures").on('click', playAllTreasures)
    $("#hand div").on('click', playTreasureCard);
};
$(document).ready(start);
