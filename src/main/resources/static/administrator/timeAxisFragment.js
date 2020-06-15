var timeAxis;
var len;

$.get("/administrator/timeAxis",function (result) {
   timeAxis = result;
   len = timeAxis.length;
   afterLoad();
});

function onEdit(e) {
    var index = $(e).index();
    $("#onEditIndex").text(index);
    $(".modal-title").text(timeAxis[index].name);
    $("#dateTimeInput").val(timeAxis[index].dateTime);
    $("#homePageNoticeInput").val(timeAxis[index].homePageNotice);
    $("#messageNoticeInput").val(timeAxis[index].messageNotice);
}

function onSubmit() {
    var index = $("#onEditIndex").text();
    var option = {
        url: "/administrator/timeAxis",
        method: "post",
        timeout: 6000,
        async: false,
        data:{
            "index":index,
            "name":timeAxis[index].name,
            "dateTimeString":$("#dateTimeInput").val(),
            "homePageNotice":$("#homePageNoticeInput").val(),
            "messageNotice":$("#messageNoticeInput").val()
        },
        success: function (data) {
            alert(data);

            if(data=="保存成功"){
                timeAxis[index].dateTime = $("#dateTimeInput").val();
                timeAxis[index].homePageNotice = $("#homePageNoticeInput").val();
                timeAxis[index].messageNotice = $("#messageNoticeInput").val();

                window.location.reload();

                $("#submit").attr("data-dismiss","modal");
                $("#submit").attr("onclick","");
                $("#submit").click();
                $("#submit").attr("data-dismiss","");
                $("#submit").attr("onclick","onSubmit()");

            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(textStatus);
        }
    };

    $.ajax(option);
}

function afterLoad() {
    (function($) {
        $.fn.uiTimeLine = function() {
            var $timeLine = $(".ui-timeLine");
            var $activeLine = $(".ui-timeLine .activeLine");
            var $dots = $(".ui-timeLine .dot");
            var $cboxs = $(".ui-timeLine .item .cbox");
            return this.each(function() {
                function setActiveLineHeight() {
                    let height = $(document).scrollTop() + window.screen.height;
                    let j = 0;
                    for (let i = 0; i < $dots.length; i++) {
                        if ($($dots[i]).offset().top < height) {
                            $($($dots[i])).addClass("active");
                            $($cboxs[i]).css({
                                "left": 0
                            });
                            j = i;
                        } else {
                            $($($dots[i])).removeClass("active")
                            $($cboxs[i]).css({
                                "left": "100vw"
                            });
                        }
                    }
                    $activeLine.css({
                        "height": $($dots[j]).offset().top - $timeLine.offset().top + 40 + "px"
                    })
                }
                $(window).on('scroll', setActiveLineHeight);
                setActiveLineHeight();
            })
        }
    })(jQuery);

    var vue_data = new Vue({
        el: ".ui-timeLine",
        data: {
            items: ["32", "21", "131", "", "", "", "", "", "", "", "", "", ""],
        },
        mounted: function() {
            $(".ui-timeLine").uiTimeLine();
        },
    });
}