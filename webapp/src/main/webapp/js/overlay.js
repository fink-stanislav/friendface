// What is $(document).ready ? See: http://flowplayer.org/tools/documentation/basics.html#document_ready
$(document).ready(function() {

    var triggers = $(".modalInput").overlay({
        // some mask tweaks suitable for modal dialogs
        mask: {
            color: '#ebecff',
            loadSpeed: 200,
            opacity: 0.9
        },

        closeOnClick: false
    });

    $("#prompt form").submit(function(e) {
        // close the overlay
        triggers.eq(1).overlay().close();
    });

});