var initializatie = function(){

	$("#player").text($("#name1").val());
		var request = $.ajax({
			cache: false,
		    url: "/DominionServer/DominionServlet",
		    data: { operation: 'initialize', name1: $("#name1").val(), name2: $("#name2").val() }
		});

		request.done(function (data) {
			var kaart1 = {
				name:data.kaart1,
				number:1
			};
			var kaart2 = {
					name:data.kaart2,
					number:2
				};
			var kaart3 = {
					name:data.kaart3,
					number:3
				};
			var kaart4 = {
					name:data.kaart4,
					number:4
				};
			var kaart5 = {
					name:data.kaart5,
					number:5
				};
			console.log(data)
			hand.push(kaart1);
			hand.push(kaart2);
			hand.push(kaart3);
			hand.push(kaart4);
			hand.push(kaart5);
			
			
			
			kingdomPile.push(data.kingdom1);
			kingdomPile.push(data.kingdom2);
			kingdomPile.push(data.kingdom3);
			kingdomPile.push(data.kingdom4);
			kingdomPile.push(data.kingdom5);
			kingdomPile.push(data.kingdom6);
			kingdomPile.push(data.kingdom7);
			kingdomPile.push(data.kingdom8);
			kingdomPile.push(data.kingdom9);
			kingdomPile.push(data.kingdom10);
			
			victoryPile.push(data.victory1);
			victoryPile.push(data.victory2);
			victoryPile.push(data.victory3);
			victoryPile.push(data.victory4);
			
			treasurePile.push(data.treasure1);
			treasurePile.push(data.treasure2);
			treasurePile.push(data.treasure3);
			
			$(".newGame").hide();
			$(".play").show();
			makeStartHand();
			addActionCards();
			addVictoryCards();
			addTreasureCards();
		    $(".big div").on('mouseenter', zoom);
		    $(".big div").on('mouseleave', unzoom);
		    $("#hand div").on('mouseenter', hoverCardsInHand);
		    $("#hand div").on('mouseleave', unHoverCardsInHand);
		    $("#hand div").on('click', playTreasureCard);
		    $("#playAllTreasures").on('click', playAllTreasures);
		    $("#actionCards div").on('click', buyKingdomCard);
		    $("#treasureCards div").on('click', buyKingdomCard);
		    $("#victoryCards div").on('click', buyKingdomCard);
		});
		
		request.fail(function (jqXHR, textStatus) {
		    alert(jqXHR.status);
	        alert(textStatus);
		});		
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
	$("#playAllTreasures").hide();
	positionCardsInHand();
}

var playTreasureCard = function(e){
	e.preventDefault();
	
	var title = $(this).attr("title");
	console.log(title);
	if(title == "copper" || title == "silver" || title == "gold"){
		var html = "<div "+"class=" + title +" "+" title="+ title + ">";
		var image = "url(../media/"+title+".jpg)";
		html += "</div>";
		$("#board").append(html);
		$("#board div").css("background-image",image);
		$(this).remove();
		
		var request = $.ajax({
			cache: false,
		    url: "/DominionServer/DominionServlet",
		    data: { operation: 'playTreasureCard', card: title, number: $(this).attr("alt") }
		});
		
		request.done(function (data) {
			console.log(data);
			var value = parseInt(data.coins);
			var action = parseInt(data.actions);
			var buys = parseInt(data.buys);
			var player = data.playerName;
			$("#coins").text(value+1);
			$("#actions").text(action);
			$("#buys").text(buys);
			$("#player").text(player);
		});
		
		request.fail(function (jqXHR, textStatus) {
		    alert(jqXHR.status);
	        alert(textStatus);
		});	
	}
	
	startState();
	positionCardsInHand();
}

var startState = function(){
	var request = $.ajax({
		cache: false,
	    url: "/DominionServer/DominionServlet",
	    data: { operation: 'startState' }
	});
}

var buyKingdomCard = function(e){
	e.preventDefault();

	var title = $(this).attr("title");
	console.log(title);
	var request = $.ajax({
		cache: false,
	    url: "/DominionServer/DominionServlet",
	    data: { operation: 'buyKingdomCard', card: title }
	});
	
}

var endTurn = function(e){
	e.preventDefault();
	var request = $.ajax({
		cache: false,
	    url: "/DominionServer/DominionServlet",
	    data: { operation: 'endTurn' }
	});
	request.done(function (data) {
		var kaart1 = {
			name:data.kaart1,
			number:1
		};
		var kaart2 = {
				name:data.kaart2,
				number:2
			};
		var kaart3 = {
				name:data.kaart3,
				number:3
			};
		var kaart4 = {
				name:data.kaart4,
				number:4
			};
		var kaart5 = {
				name:data.kaart5,
				number:5
			};
		console.log(data)
		hand = [];
		hand.push(kaart1);
		hand.push(kaart2);
		hand.push(kaart3);
		hand.push(kaart4);
		hand.push(kaart5);
		$("#player").html(data.activePlayer);
		$("#coins").text("0");
		$("#actions").text("1");
		$("#buys").text("1");
		deletePreviousCards();
		makeStartHand();
		$(".big div").on('mouseenter', zoom);
	    $(".big div").on('mouseleave', unzoom);
	    $("#hand div").on('mouseenter', hoverCardsInHand);
	    $("#hand div").on('mouseleave', unHoverCardsInHand);
	    $("#hand div").on('click', playTreasureCard);
	    $("#playAllTreasures").on('click', playAllTreasures);
	    $("#actionCards div").on('click', buyKingdomCard);
	    $("#treasureCards div").on('click', buyKingdomCard)
	    $("#victoryCards div").on('click', buyKingdomCard)
	    
	});
	
	request.fail(function (jqXHR, textStatus) {
	    alert(jqXHR.status);
        alert(textStatus);
	});	
}

var start = function () {
	$("#initialize").on('click', initializatie);
	$("#endTurn").on('click', endTurn);
};
$(document).ready(start);