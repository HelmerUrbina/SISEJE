function ntab(evt, obj) {
    var nav4 = window.Event ? true : false;
    var key = nav4 ? evt.which : evt.keyCode;
    if (key === 13) {
        $("#" + obj).focus();
    }
    if (key === 34)
        return (key === 0);
    if (key === 37)
        return (key === 0);
}
﻿function ntabGrabar(evt, obj) {
    var nav4 = window.Event ? true : false;
    var key = nav4 ? evt.which : evt.keyCode;
    if (key === 13) {
        GuardarDatos();
    }
    if (key === 34)
        return (key === 0);
    if (key === 37)
        return (key === 0);
}
function pad(str, max) {
    str = str.toString();
    return str.length < max ? pad("0" + str, max) : str;
}