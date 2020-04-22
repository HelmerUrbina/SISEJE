$(function () {
//Textare auto growth
    autosize($('textarea.auto-growth'));
    //Datetimepicker plugin
    $('.datetimepicker').bootstrapMaterialDatePicker({
        format: 'dddd DD MMMM YYYY - HH:mm',
        clearButton: true,
        weekStart: 1
    });
    $('.datepicker').bootstrapMaterialDatePicker({
        lang : 'es',
        format: "DD/MM/YYYY",
        weekStart: 1,
        startDate: true,
        time: false,
        forceParse: false,
        autoclose: true       
    });
    $('.timepicker').bootstrapMaterialDatePicker({
        format: 'HH:mm',
        clearButton: true,
        date: false
    });
});