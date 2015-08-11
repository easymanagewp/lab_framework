/**
 * Created by aaron on 15/7/16.
 */
$(function() {
    menuTree();
    toolbarR();
    autoIframeHeight();
    /*$('a').click(function() {
        $("#MianFrame").attr('src',$(this).attr('href'))
        //autoIframeHeight();

        //alert($(this).attr('href'));
        return false;
    });*/
});

function menuTree() {
    $('#shuojin').click(function() {
        if ( $('.menubox').hasClass('shou') ) {
            $('.menubox').removeClass('shou');
            $('.mianframe').removeClass('suojin');
        }
        else {
            $('.menubox').addClass('shou');
            $('.mianframe').addClass('suojin');
        }
    });
    $('.menu_tree>li>a').click(function() {
        if ( $(this).closest('.menubox').hasClass('shou') ) {
            $('.menu_tree>li>ul').not($(this).next()).hide();
            $(this).next().toggle();

            $('.menu_tree>li.open').not($(this).parent()).removeClass('open');
            if ( $(this).parent().hasClass('open') ) {
                $(this).parent().removeClass('open');
            }
            else {
                $(this).parent().addClass('open');
            }
        }
        else {
            $('.menu_tree>li>ul').not($(this).next()).slideUp(300);
            $(this).next().slideToggle(300);

            $('.menu_tree>li.open').not($(this).parent()).removeClass('open');
            if ( $(this).parent().hasClass('open') ) {
                $(this).parent().removeClass('open');
            }
            else {
                $(this).parent().addClass('open');
            }
        }
        return false;
    });

    $('.menu_tree>li>ul>li>a').click(function() {
        $(this).closest('ul').find('.open').not($(this).parent()).removeClass('open');

        $(this).closest('ul').find('ul').not($(this).next()).slideUp(300);

        if ( $(this).parent().hasClass('have3') ) {

            $(this).next().slideToggle(300);

            if ( $(this).parent().hasClass('open') ) {
                $(this).parent().removeClass('open');
            }
            else {
                $(this).parent().addClass('open');
            }

        }
        else {
            $('.menu_tree .active').removeClass('active');
            $(this).addClass('active');
        }
        return false;
    });

    $('.menu_tree .have3 .submenu a').click(function() {
        $('.menu_tree .active').removeClass('active');
        $(this).addClass('active');
        return false;
    });
}

function toolbarR() {

    $('.toolbar_r>li').each(function() {

        //alert($('.toolbar_r').offset().left);
        var r = $('.header').width() - $(this).children('a').find('img').offset().left;


        if ( !$(this).children('ul').hasClass('xl_usercz') ) {
            if( $(this).children('ul').hasClass('xl_email')) {
                $(this).children('ul').css('right','200px');
            }
            else {
                $(this).children('ul').css('right','250px');
            }

        }
    }).click(function() {
        $('.dropdown_menu').not($(this).children('ul')).hide();
        $(this).children('ul').toggle();
        return false;
    });

    $('body').click(function() {
        $('.dropdown_menu').hide();
        if ( $('.menubox').hasClass('shou') ) {
            $('.menu_tree>li>ul').hide();
            $('.menu_tree>li').removeClass('open');
        }
    });

}

function autoIframeHeight() {
    $("#MianFrame").load(function(){
        var mainheight = $(this).contents().find("body")[0].scrollHeight + 30;
        $(this).height(mainheight);
        $('.menubox').height(mainheight);
        $(this).contents().find("body").click(function() {
            $('.dropdown_menu').hide();
            if ( $('.menubox').hasClass('shou') ) {
                $('.menu_tree>li>ul').hide();
                $('.menu_tree>li').removeClass('open');
            }
        });
        $(this).contents().find("a").click(function() {
            var mainheight = $(this).closest('body')[0].scrollHeight + 30;
            $('#MianFrame', parent.document).height(mainheight);
            $('.menubox').height(mainheight);
        });
    });
}