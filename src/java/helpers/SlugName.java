/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author user
 */
public class SlugName {
       public static String convertToSlugName(String name){

    String greeklish = 
            name
            .replaceAll(" ", "-")
            .replaceAll("α", "a")
            .replaceAll("ά", "a")
            .replaceAll("Α", "a")
            .replaceAll("Ά", "a")
            .replaceAll("β", "b")
            .replaceAll("Β", "b")
            .replaceAll("γ", "g")
            .replaceAll("Γ", "g")
            .replaceAll("δ", "d")
            .replaceAll("Δ", "d")
            .replaceAll("ε", "e")
            .replaceAll("έ", "e")
            .replaceAll("Ε", "e")
            .replaceAll("Έ", "e")    
            .replaceAll("ζ", "z")
            .replaceAll("Ζ", "z")
            .replaceAll("η", "i")
            .replaceAll("ή", "i")
            .replaceAll("Ή", "i")
            .replaceAll("θ", "th")
            .replaceAll("Θ", "th")
            .replaceAll("ι", "i")
            .replaceAll("ί", "i")
            .replaceAll("ϊ", "i")
            .replaceAll("Ί", "i")
            .replaceAll("Ι", "i")
            .replaceAll("κ", "k")
            .replaceAll("Κ", "k")
            .replaceAll("λ", "l")
            .replaceAll("Λ", "l")
            .replaceAll("μ", "m")
            .replaceAll("Μ", "m")
            .replaceAll("ν", "n")
            .replaceAll("Ν", "n")
            .replaceAll("ξ", "ks")
            .replaceAll("Ξ", "ks")
            .replaceAll("ο", "o")
            .replaceAll("ό", "o")
            .replaceAll("Ό", "o")
            .replaceAll("Ο", "o")
            .replaceAll("π", "p")
            .replaceAll("Π", "p")
            .replaceAll("ρ", "r")
            .replaceAll("Ρ", "r")
            .replaceAll("σ", "s")
            .replaceAll("Σ", "s")
            .replaceAll("ς", "s")
            .replaceAll("τ", "t")
            .replaceAll("Τ", "t")
            .replaceAll("υ", "u")
            .replaceAll("ύ", "u")
            .replaceAll("Ύ", "y")
            .replaceAll("Υ", "y")
            .replaceAll("φ", "f")
            .replaceAll("Φ", "f")
            .replaceAll("χ", "x")
            .replaceAll("Χ", "x")
            .replaceAll("ψ", "ps")
            .replaceAll("Ψ", "ps")
            .replaceAll("ω", "w")
            .replaceAll("ώ", "w")
            .replaceAll("΄Ώ", "w")
            .replaceAll("Ω", "w")
            .replaceAll("!", "")
            .replaceAll("!", "")
            .replaceAll("@", "")
            .replaceAll("#", "")
            .replaceAll("$", "")
            .replaceAll("%", "")
            .replaceAll("&", "")
            .replaceAll("=", "")
            .replaceAll("}", "")
            .replaceAll("]", "")
            .replaceAll("|", "")
            .replaceAll("΅", "")
            .replaceAll("'", "")
            .replaceAll(";", "")
            .replaceAll(":", "")
            .replaceAll("/", "")
            .replaceAll(">", "")
            .replaceAll("<", "")
            .replaceAll("`", "")
            .replaceAll("~", "");
    
        return greeklish;
    }
}
