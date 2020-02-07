        document.getElementById("form:sumBotton").style.display="none";
                            document.getElementById("nullMessage").style.display="block";
                            document.getElementById("nullMessage").innerHTML = "Δώστε σωστή τιμή στην ποσότητα του προϊόντος";
                            
                    function countPrice(){
                        var e = document.getElementById("form:productdropDown");
                        var strUser = e.options[e.selectedIndex].text;
                        var res = strUser.split("-");
                        var price =  res[1].trim();
                        price = price.replace("€", "");
                        var qty = Number(document.getElementById("form:qty").value);
                        var totalPrice = price * qty;
                        
                           
                            
                        if ( isNaN(totalPrice) || qty === 0.0 || qty < 0 ){
                            document.getElementById("form:sumBotton").style.display="none";
                            document.getElementById("nullMessage").style.display="block";
                            document.getElementById("nullMessage").innerHTML = "Δώστε σωστή τιμή στην ποσότητατου προϊόντος";
                        }else{
                            document.getElementById("nullMessage").style.display="none";
                            document.getElementById("form:sumBotton").style.display="block";
                        }
                        document.getElementById("tp").innerHTML = "Τελική Τιμή: " + Math.round(totalPrice.toFixed(2)*1.24*100)/100+"€ Με ΦΠΑ";
                     }
                     
                     
         