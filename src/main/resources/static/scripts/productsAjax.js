<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        function toggleFormAdd(){
            $("#form-add").toggle();
        }
        function deleteResultMessage(){
            $("#result-message").addClass("magictime puffOff");
        }
        function sendAndSaveProductRequest(){
            var nameProduct=$("#nameProduct").val();
            var brandProduct=$("#brandProduct").val();
            var cost = $("#costProduct").val();
            var quantity = $("#quantityProduct").val();
            var imgSrc = $("#imgSrc").val();
            var url = "http://localhost:8080/admin/products?name="+nameProduct+
            "&brand="+brandProduct+"&cost="+cost+"&quantity="+quantity+"&imgSrc="+imgSrc;
            $.post(url, function(data){
                $("#result-message").html(data);
                $("#result-message").removeClass("magictime puffOff");
                $("#result-message").show();
                setTimeout(deleteResultMessage, 2000);
                $("#form-add").toggle();

            }).done(function(response){
                setTimeout(function(){
                    location.reload();
                }, 2000);
            });
        }

        function deleteProduct(productId){
        $.ajax({url:"/admin/products/"+productId,
            type:"delete"});

        //stergem din interfata

        $("#"+productId).parent().parent().remove();
        $("#result-message").html("Produsul a fost sters");
        $("#result-message").removeClass("magictime puffOut");
        $("#result-message").show();
        setTimeout(deleteResultMessage, 2000);

        }