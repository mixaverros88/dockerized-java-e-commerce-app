     function changeQTY(action){
                 var qty = document.getElementById("form:qty").value;
                 
                 
//                 if (qty == 0 ) {
//                    document.getElementById("form:sentToWhishListButton").value = "Επiλέξτε ποσότητα";
//                }else{
//                    document.getElementById("form:sentToWhishListButton").value = "Εισαγωγή στην Wishlist";
//                }
                
                 if( action ==='+'){
                   document.getElementById("form:qty").value=(Math.round(qty))+1;
                 }else if(action ==='-' && qty>0){
                     document.getElementById("form:qty").value=(Math.round(qty))-1;
                 }
                 chechqty();
             
             }
       



            function string_to_slug(str) {

                str = document.getElementById("payment:holderName").value;
                

                from = [ "ου", "ΟΥ", "Ού", "ού", "αυ", "ΑΥ", "Αύ", "αύ", "ευ", "ΕΥ", "Εύ", "εύ", "α", "Α", "ά", "Ά", "β", "Β", "γ", "Γ", "δ", "Δ", "ε", "Ε", "έ", "Έ", "ζ", "Ζ", "η", "Η", "ή", "Ή", "θ", "Θ", "ι", "Ι", "ί", "Ί", "ϊ", "ΐ", "Ϊ", "κ", "Κ", "λ", "Λ", "μ", "Μ", "ν", "Ν", "ξ", "Ξ", "ο", "Ο", "ό", "Ό", "π", "Π", "ρ", "Ρ", "σ", "Σ", "ς", "τ", "Τ", "υ", "Υ", "ύ", "Ύ", "ϋ", "ΰ", "Ϋ", "φ", "Φ", "χ", "Χ", "ψ", "Ψ", "ω", "Ω", "ώ", "Ώ" ];
                to   = [ "ou", "ou", "ou", "ou", "au", "au", "au", "au", "eu", "eu", "eu", "eu", "a", "a", "a", "a", "b", "b", "g", "g", "d", "d", "e", "e", "e", "e", "z", "z", "i", "i", "i", "i", "th", "th", "i", "i", "i", "i", "i", "i", "i", "k", "k", "l", "l", "m", "M", "n", "n", "ks", "ks", "o", "o", "o", "o", "p", "p", "r", "r", "s", "s", "s", "t", "t", "y", "y", "y", "y", "y", "y", "y", "f", "f", "x", "x", "ps", "ps", "o", "o", "o", "o" ];

                for ( var i = 0; i < from.length; i++ ) {

                    while( str.indexOf( from[i]) !== -1 ){

                        str = str.replace( from[i], to[i] );    // CONVERT GREEK CHARACTERS TO LATIN LETTERS

                    }

                }

               // str = str.replace(/[^a-z0-9 -]/g, '') // REMOVE INVALID CHARS
                         //.replace(/\s+/g, '-')        // COLLAPSE WHITESPACE AND REPLACE BY DASH - 
                         //.replace(/-+/g, '-');        // COLLAPSE DASHES

             document.getElementById("payment:holderName").value=str;

            }


        function validationForm(){
          var creditNumber = document.forms["pay"]["holderCard"].value;
          alert(creditNumber);
        }

        function visaPayf(imgeSource){
          document.getElementById('visaPay').style.display = "block";
          document.getElementById('paymentImage').src ="http://localhost:8081/PrimeFaces/faces/resources/images/"+imgeSource;
        }
  

        function calculateCredits() {
                                    var checkBox = document.getElementById("myCheck");
                                    var totalCredits = document.getElementById("totalCredits").innerHTML;
                                    var totalPrice = document.getElementById("getTotalPriceHidden").innerHTML;
                                    if (Number(totalCredits) <= Number(totalPrice)){
                                        if (checkBox.checked === true){
                                           totalPrice = Math.round((totalPrice - totalCredits)*100)/100;
                                            document.getElementById("getTotalPrice").innerHTML=totalPrice;
                                            document.getElementById("sendCredits").value=totalCredits;
                                        } else {
                                            document.getElementById("getTotalPrice").innerHTML=totalPrice;
                                             document.getElementById("sendCredits").value=0;
                                        }
                                        document.getElementById("messageCredit").innerHTML='';
                                    }else{
                                        document.getElementById("messageCredit").innerHTML='Τα Credits δεν πρέπει να ξεπερνουν την αξία αγοράς';
                                    }
                                    

        }
                   
       
       function qntSetZero(){
             
           document.getElementById("form:qty").value=0;
       }
       
       
       
       
       function chechqty(){
            
            document.getElementById("form:maxQty").readOnly = true;
            
            
            var qty = document.getElementById("form:qty").value;
//            var vat = document.getElementById("vat").value;
            var priceWithVat =document.getElementById("priceWithVat").value;
            var priceVat =  Number((qty * priceWithVat).toFixed(2)); 
            var maxQty =  Number(document.getElementById("form:maxQty").value);

console.log(qty);
                document.getElementById("sendQty").value = qty;
                
                document.getElementById("tpBox").style.display = "block";
                document.getElementById("tp").innerHTML = "Τελική Τιμή: <span class='totalx mainColor'>" + priceVat+"€</span>";
              if(qty > 0){
                  document.getElementById("form:sentToWhishListButton").style.display = "inline-flex";
                   document.getElementById("wlMesaage").style.display = "none";
              }
            if (qty > 0 && qty <= maxQty) {
                
                document.getElementById("form:sentToCartButton").style.display = "block";
                document.getElementById("messageForButton").style.display = "none";
                document.getElementById("wlMesaage").style.display = "none";
                
                //document.getElementById("form:sentToWhishListButton").style.display = "none"; //whishlist
            }else if( qty > maxQty ){
                //document.getElementById("form:sentToWhishListButton").style.display = "block"; //whishlist
                
                document.getElementById("form:sentToCartButton").style.display = "none";
                document.getElementById("messageForButton").style.display = "block";
                document.getElementById("messageForButton").innerHTML="Η ποσότητα που επιλέξατε δεν είναι διαθέσιμη.<br/>Διαθέση ποσότητα προϊόντος: "+maxQty+" <br/> <bold>Εάν ενδιαφέρεστε για το προϊόν μπορείτε να το βάλετε στο whishlist και θα σας ειδοποιήσουμε όταν είναι διαθέσιμο.</bold>";
            
            }else if(qty === "" || qty == 0 ){    
                document.getElementById("form:sentToWhishListButton").style.display = "none";
                document.getElementById("form:sentToCartButton").style.display = "none";
                document.getElementById("messageForButton").style.display = "block";
                document.getElementById("messageForButton").innerHTML="Επιλέξτε ποσότητα";
                 document.getElementById("wlMesaage").style.display = "inline-flex";
                
            }else if(isNaN(qty)){
                document.getElementById("form:sentToWhishListButton").style.display = "none";
                document.getElementById("form:sentToCartButton").style.display = "none";
                document.getElementById("messageForButton").style.display = "block";
                document.getElementById("messageForButton").innerHTML="Μη αποδεκτές μονάδες μέτρησης";
                document.getElementById("tpBox").style.display = "none";
            }



        }
        
       
            

    function setCookie(cname,cvalue,exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires=" + d.toGMTString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function getCookie(cname) {
        var name = cname + "="; //username=

        //document.cookie--> Read all cookies accessible from this location
        var decodedCookie = decodeURIComponent(document.cookie);

        var ca = decodedCookie.split(';');
        for(var i = 0; i < ca.length; i++) {

            //c.charAt(0) --> return the fist character of a string
            //substring(1) --> return the string with out the fist charachter
            var c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }

            // indexOf -->  returns the position of given string 
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function checkCookie() {

        //check if cookie is old or new
        var user=getCookie("cookies-consent");

        // if the user is old
        if (user !== "") { 
            document.getElementById("new").style.display = "none";
        }else{
		document.getElementById("new").style.display = "block";
	}
    }


  function createCookie() {
        //hide div
        document.getElementById("new").style.display = "none";

        //create a cookie
        setCookie("cookies-consent", "ezikos", 30);
    }


            
            
       
        
