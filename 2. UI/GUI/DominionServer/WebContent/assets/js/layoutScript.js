var fotos = ["dominionSlide1", "dominionSlide2", "dominionSlide3", "dominionSlide1"];
var otherCards=["back","trash"]
var actionCards=[];
var hand = [];
var kingdomPile = [];
var victoryPile = [];
var treasurePile = [];
var huidigeAfbeelding = 1;
var timer;


var newGame = function(e){
	e.preventDefault();
	$(".startGame").hide();
	$(".newGame").show();
}


var addAmounts = function(){
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
		var image = "url(../media/" + kingdomPile[i] + ".jpg";
		i++;
		$("#actionCards div:nth-child(" + i + ")").css("background-image", image);
		$("#actionCards div:nth-child(" + i + ")").attr("title", kingdomPile[i-1]);
		i--;	
	}
}

var addTreasureCards = function(){

	var i = 1;
	for(i; i<treasurePile.length+1; i++){
		var title = $("#treasureCards div:nth-child(" + i + ")").attr("title");
		var image = "url(../media/"+ title + ".jpg)";
		$("#treasureCards div:nth-child(" + i + ")").css("background-image", image);
	}
}

var addVictoryCards = function(){
	var i = 1;
	for(i; i<victoryPile.length + 1; i++){
		var title = $("#victoryCards div:nth-child(" + i + ")").attr("title");
		var image = "url(../media/"+ title + ".jpg)";
		$("#victoryCards div:nth-child(" + i + ")").css("background-image", image);
	}
}

var deletePreviousCards = function(){
	$("#board div").remove();
	$("#hand div").remove();
}

var makeStartHand = function(){
	var i = 0;
	for(i;i<5;i++){
		var title = hand[i].name;
		var number = hand[i].number;
		var html = "<div "+"class=" + title +" "+" title="+ title + " " +" alt="+number+ ">";
		var image = "url(../media/"+title+".jpg)";
		html += "</div>";
		$("#hand").append(html);
		$("."+hand[i].name).css("background-image", image);
	}
	positionCardsInHand();
}

var zoom = function(){
	$("#zoom").show();
	var image = "url(../media/"+ $(this).attr("title") + ".jpg)";
	$("#zoom").css("background-image", image);
}

var unzoom = function(){
	$("#zoom").hide();
}

var positionCardsInHand = function(){
	var positionCards = 0;
	var zIndex = 1;
	for(i = 1;i<=hand.length;i++){
		$("#hand div:nth-child(" + i + ")").attr("alt", i);
		$("#hand div:nth-child(" + i + ")").css("left", positionCards+"%")
		positionCards+= 10;
		zIndex += 10;
	}
}

var hoverCardsInHand = function(){
	$(this).css("bottom", "11%");
}

var unHoverCardsInHand = function(){
	$(this).css("bottom", "");
}

var start = function () {
    addAmounts();
    addActionCards();
    addTreasureCards();
    addVictoryCards();
    $(".startGame").show();
    $("#newGame").on('click', newGame);

};
$(document).ready(start);
