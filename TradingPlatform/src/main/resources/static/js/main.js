$(document).ready(function() {
    var bidall = [];
    var offerall = [];
    var apple = {
        bid: [],
        offer: []
    };
    var citi = {
        bid: [],
        offer: []
    };
    var abt = {
        bid: [],
        offer: []
    };
    var ibm = {
        bid: [],
        offer: []
    };
    var symbols = [];

    var utils = {
        showsymbolprice: function(number) {
            $(".bestprice").hide();
            $(".symbolprice").show();
            $(".symbolprice").find("tbody").find("tr").remove();


            for (var i = 0; i < symbols[number].bid.length; i++) {
                var newrow = '<tr><td>' + symbols[number].bid[i].price + '</td><td>' + symbols[number].bid[i].quantity + '</td></tr>';
                $(".bidprice").find('tbody').append(newrow);
            }
            for (var i = 0; i < symbols[number].offer.length; i++) {
                var newrow = '<tr><td>' + symbols[number].offer[i].price + '</td><td>' + symbols[number].offer[i].quantity + '</td></tr>';
                $(".offerprice").find('tbody').append(newrow);
            }

        },
        showbestdata: function() {
            $(".bestprice").show();
            $(".bestprice").find('tbody').find("tr").remove();
            for (var i = 0; i < symbols.length; i++) {
                var a = 0,
                    b = 0,
                    c = 0,
                    d = 0,
                    e = 0;
                if (symbols[i].bid[0] != undefined) {
                    a = symbols[i].bid[0].symbol;
                    b = symbols[i].bid[0].price;
                    c = symbols[i].bid[0].quantity;
                }
                if (symbols[i].offer[0] != undefined) {
                    a = symbols[i].offer[0].symbol;
                    d = symbols[i].offer[0].price;
                    e = symbols[i].offer[0].quantity;
                }
                var newrow = '<tr><td>' + a + '</td><td>' + b + '</td><td>' + c + '</td><td>' + d + '</td><td>' + e + '</td></tr>';
                $(".bestprice").find('tbody').append(newrow);
            }
        }

    }

    $.get("./orderbook/getAllSorted?type=B", function(result) {
        bidall = result;
        for (var i = 0; i < bidall.length; i++) {
            switch (bidall[i].symbol) {
                case 'APPLE':
                    apple.bid.push(bidall[i])
                    break;
                case 'CITI':
                    citi.bid.push(bidall[i])
                    break;
                case 'ABT':
                    abt.bid.push(bidall[i])
                    break;
                case 'IBM':
                    ibm.bid.push(bidall[i])
                    break;
                default:
                    break;
            }
        }

        $.get("./orderbook/getAllSorted?type=O", function(result) {
            offerall = result;
            for (var i = 0; i < offerall.length; i++) {
                switch (offerall[i].symbol) {
                    case 'APPLE':
                        apple.offer.push(offerall[i])
                        break;
                    case 'CITI':
                        citi.offer.push(offerall[i])
                        break;
                    case 'ABT':
                        abt.offer.push(offerall[i])
                        break;
                    case 'IBM':
                        ibm.offer.push(offerall[i])
                        break;
                    default:
                        break;
                }

            }
            symbols.push(apple, citi, abt, ibm);
            utils.showbestdata();

        });
    });



    $("#submit").click(function(event) {

    	if ($('.quantity').val()=='') {
    		alert("You should input right data!!!");
    		return ;
    	}
        if ($('.symbol option:selected').get(0).selectedIndex == 2 || $('.symbol option:selected').get(0).selectedIndex == 3) {
            $('.price').removeAttr("disabled");
        }

        var order = {
            side: $("input[name='optionsRadiosinline']:checked").val(),
            symbol: $('.symbol option:selected').text(),
            quantity: $('.quantity').val(),
            price: $('.price').val(),
            ordertype: $('.ordertype option:selected').val(),
        }

        var url = 'order/execute?' + 'orderType=' +
            order.ordertype + '&symbol=' + order.symbol +
            '&side=' + order.side + '&quantity=' +
            order.quantity + '&price=' + order.price + '&traderID=' + 0 + "&cancelTime=" + "2013-8-23 4:55:19";


        $.ajax({
            type: "GET",
            url: url,
            success: function(data) {
                if (data.data == null)
                    alert("返回的是null")
            }
        })
    })

     $(".ordertype").change(function() {
        var selectIndex = $(".ordertype").get(0).selectedIndex;
        if (selectIndex==0 || selectIndex==1 ){
        	$('.price')..attr("disabled","disabled");
        }else{
        	
        	$('.price').removeAttr("disabled");
        }


    });   

    $("#symbolselect").change(function() {
        var selectIndex = $("#symbolselect").get(0).selectedIndex;
        switch (selectIndex) {
            case 0:
                $(".symbolprice").hide();
                utils.showbestdata();
                break;
            case 1:
                utils.showsymbolprice(0);
                break;
            case 2:
                utils.showsymbolprice(1);
                break;
            case 3:
                utils.showsymbolprice(2);
                break;
            case 4:
                utils.showsymbolprice(3);
                break;

            default:
                // statements_def
                break;
        }

    });

})