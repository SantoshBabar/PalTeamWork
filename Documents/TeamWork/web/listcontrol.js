/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 $(function () {
            
//            function addweight() {
//                var textbox = document.createElement('input');
//                textbox.type = 'text';
//                textbox.
//                $("#test").append(textbox);
//               }
     
            function moveLRItems(origin, dest) 
            {
              
                $("#test").html("");
           }
            
    function moveRLItems(origin, dest) 
    {

        $(origin).find(':selected').each(function ()
        {
            $('<tr><td><input type="text" name="' + this.value + '" value="' + this.text + '" disabled style="height:22px;background-color:Black;color:white"/></td><td><input type="text" name="' + this.value + '" style="height:22px"/></td></tr>').appendTo("#test");
             });

    }

//            function moveAllItems(origin, dest) {
//                $(origin).children().appendTo(dest);
//            }

            $('#left').click(function () {
                moveLRItems('#test0', '#sbOne');
            });

            $('#right').on('click', function () {
                moveRLItems('#sbOne', '#test0');
            });

            $('#leftall').on('click', function () {
                moveAllItems('#sbTwo', '#sbOne');
            });

            $('#rightall').on('click', function () {
                moveAllItems('#sbOne', '#sbTwo');
            });
            
            $("#submit").click(function(e) {
    e.preventDefault();
    var values = $.map($('#sbTwo option'), function(e) { return e.value; });
    
    $.ajax({
        type: "POST",
        url: "/spring-css/valueSubmit",
        data: { 
            selectedList:values
            
        },
        success: function(result) {
            alert('ok');
        },
        error: function(result) {
            alert('error');
        }
    });
});
        });
        
        
