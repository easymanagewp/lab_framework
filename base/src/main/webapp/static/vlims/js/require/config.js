/**
 * Created by aaron on 15/7/30.
 */
requirejs.config({
    //baseUrl : '/static/component/',
    baseUrl : './',
    map: {
        '*': {
           // 'css': '../requirejs/css'
        }
    },
    paths : {
        'formValidate':'js/jquery/jquery.form'
    }
});



require(['formValidate'],function(formValidate){
    formValidate();
})