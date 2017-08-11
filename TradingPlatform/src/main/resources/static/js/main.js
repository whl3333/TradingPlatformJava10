$(document).ready(function() {

    var symbols = [];
    var symbolname = [];

    function loaddata() {
        symbols = [];
        symbolname = [];
        $.ajax({
            type: "GET",
            url: "./orderbook/getAllSorted?type=B",
            async: false,
            success: function(result) {
                var bidall = result;
                selectsymbol(bidall);
            }
        });
        $.ajax({
            type: "GET",
            url: "./orderbook/getAllSorted?type=O",
            async: false,
            success: function(result) {
                var offerall = result;
                selectsymbol(offerall);
                $(".symbol option").remove();
                $('.symbolname option').not(":first").remove();

                for (var i = 0; i < symbolname.length; i++) {
                    $(".symbol").append('<option>' + symbolname[i] + '</option>');
                    $(".symbolname").append('<option>' + symbolname[i] + '</option>');
                    symbols.push();
                }

            }
        });
    }

    function selectsymbol(array) {

        for (var i = 0; i < array.length; i++) {

            var index = symbolname.indexOf(array[i].symbol);
            if (index != -1) {
                if (array[i].type == "O") {
                    symbols[index].offer.push(array[i])
                }
                if (array[i].type == "B") {
                    symbols[index].bid.push(array[i])
                }

            } else {
                symbolname.push(array[i].symbol);
                var obj = new Object();
                obj.bid = [];
                obj.offer = [];

                if (array[i].type == "O") {
                    obj.offer.push(array[i])
                }
                if (array[i].type == "B") {
                    obj.bid.push(array[i])
                }
                symbols.push(obj);
            }

        }
    }

    var utils = {
        showsymbolprice: function(number) {
            $(".bestpricediv").hide();
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
            $(".bestpricediv").show();
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


    loaddata();
    utils.showbestdata();




    $("#submit").click(function(event) {

        if ($('.quantity').val() == '') {
            alert("You should input right data!!!");
            return;
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
                if (data.message == "ACTIVE") {
                    alert("Order Status : PENDING");
                    return;
                }
                if (data.data == null) {
                    alert("Order Status : FAILED");
                } else {

                    var exct = data.data;
                    var result = "Your order's Status :" + "\n";
                    for (var i = 0; i < exct.length; i++) {
                        result += 'EXECUTION(' + (i + 1) + ')  Status : ' + exct[i].result + "\n";
                    }
                    alert(result);

                    loaddata();
                }
            }
        })

    });

    $(".ordertype").change(function() {
        var selectIndex = $(".ordertype").get(0).selectedIndex;
        if (selectIndex == 0 || selectIndex == 1) {
            $('.price').attr("disabled", "disabled");
        } else {
            $('.price').removeAttr("disabled");
        }


    });

    $("#symbolselect").change(function() {
        var selectIndex = $("#symbolselect").get(0).selectedIndex;
        if (selectIndex == 0) {
            $(".symbolprice").hide();
            utils.showbestdata();
        } else {
            utils.showsymbolprice(selectIndex - 1);
            $(".symbol").find("option").eq(selectedIndex - 1).attr("selected", true);
        }

    });

})