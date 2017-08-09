$(function() {
	var bidall = []; 
	var offerall = [];
	var allSymbol = [];
	
	var symbolItem = {
		symbol,
		bid[],
		offer[]
	};
	
	var bid = {
			bidPrice,
			bidQuantity
	};
	
	var offer = {
			offerPrice,
			offerQuantity
	};
	
	var apple = {
		bid : [],
		offer : []
	};
	var citi = {
		bid : [],
		offer : []
	};
	var abt = {
		bid : [],
		offer : []
	};
	var ibm = {
		bid : [],
		offer : []
	};
	
	$.get("./orderbook/getAllSorted?type=B", function(result) {
		bidall = result;
//		for (var i = 0; i < bidall.length; i++) {
//			switch (bidall[i].symbol) {
//			case 'APPLE':
//				apple.bid.push(bidall[i])
//				break;
//			case 'CITI':
//				citi.bid.push(bidall[i])
//				break;
//			case 'ABT':
//				abt.bid.push(bidall[i])
//				break;
//			case 'IBM':
//				ibm.bid.push(bidall[i])
//				break;
//			default:
//				break;
//			}
		}
	});

	$.get("./orderbook/getAllSorted?type=O", function(result) {
		offerall = result;
//		for (var i = 0; i < offerall.length; i++) {
//			switch (offerall[i].symbol) {
//			case 'APPLE':
//				apple.offer.push(offerall[i])
//				break;
//			case 'CITI':
//				citi.offer.push(offerall[i])
//				break;
//			case 'ABT':
//				abt.offer.push(offerall[i])
//				break;
//			case 'IBM':
//				ibm.offer.push(offerall[i])
//				break;
//			default:
//				break;
//			}
		}
	});
	
	$.get("./orderbook/getSymbols", function(result) {
		symbols = result;	
	});

	var showsymbolprice : function(symbol) {

		$(".bidprice").find('tr').eq(1).find('td').eq(0).text(
				symbol.bid[0].price);
		$(".bidprice").find('tr').eq(1).find('td').eq(1).text(
				symbol.bid[0].quantity);
		$(".bidprice").find('tr').eq(2).find('td').eq(0).text(
				symbol.bid[1].price);
		$(".bidprice").find('tr').eq(2).find('td').eq(1).text(
				symbol.bid[1].quantity);
		$(".bidprice").find('tr').eq(3).find('td').eq(0).text(
				symbol.bid[2].price);
		$(".bidprice").find('tr').eq(3).find('td').eq(1).text(
				symbol.bid[2].quantity);

		$(".offerprice").find('tr').eq(1).find('td').eq(0).text(
				symbol.offer[0].price);
		$(".offerprice").find('tr').eq(1).find('td').eq(1).text(
				symbol.offer[0].quantity);
		$(".offerprice").find('tr').eq(2).find('td').eq(0).text(
				symbol.offer[1].price);
		$(".offerprice").find('tr').eq(2).find('td').eq(1).text(
				symbol.offer[1].quantity);
		$(".offerprice").find('tr').eq(3).find('td').eq(0).text(
				symbol.offer[2].price);
		$(".offerprice").find('tr').eq(3).find('td').eq(1).text(
				symbol.offer[2].quantity);

	};
	
	var showbestdata : function() {
		
		$(".bestprice").find('tr').eq(1).find('td').eq(1).text(
				apple.bid[0].price);
		$(".bestprice").find('tr').eq(1).find('td').eq(2).text(
				apple.offer[0].price);
		$(".bestprice").find('tr').eq(2).find('td').eq(3).text(
				citi.bid[0].price);
		$(".bestprice").find('tr').eq(2).find('td').eq(4).text(
				citi.offer[0].price);

		$(".bestprice").find('tr').eq(3).find('td').eq(0).text(
				abt.bid[0].price);
		$(".bestprice").find('tr').eq(3).find('td').eq(1).text(
				abt.offer[0].price);

		$(".bestprice").find('tr').eq(4).find('td').eq(0).text(
				ibm.bid[0].price);
		$(".bestprice").find('tr').eq(4).find('td').eq(1).text(
				ibm.offer[0].price);
		
	};
	
	this.showbestdata();

	$("#symbolselect").change(changeselect());
	function changeselect() {
		var selectIndex = $("#symbolselect").get(0).selectedIndex;
		switch (selectIndex) {
		case 0:
			utils.showsymbolprice(apple);
			break;
		case 1:
			utils.showsymbolprice(citi);
			break;
		case 2:
			utils.showsymbolprice(abt);
			break;
		case 3:
			utils.showsymbolprice(ibm);
			break;
		default:
			// statements_def
			break;
		}
	};

	$("#submit")
			.click(
					function(event) {

						var order = {
							side : $(
									"input[name='optionsRadiosinline']:checked")
									.val(),
							symbol : $('.symbol option:selected').text(),
							quantity : $('.quantity').val(),
							price : $('.price').val(),
							ordertype : $('.ordertype option:selected').text(),
						}

						var url = '.order/execute?' + 'ordertype='
								+ order.ordertype + 'ordertype='
								+ order.ordertype + '&symbol=' + order.symbol
								+ '&side=' + order.side + '&quantity='
								+ order.quantity + '&price=' + order.price;

						$.ajax({
							type : "GET",
							url : url,
							success : function(data) {
								console.log(data);
							}
						})
					});

})