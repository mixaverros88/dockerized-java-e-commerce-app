package helpers;

import org.apache.log4j.Logger;

/**
 * <h1>Convert To Greeklish</h1>
 *
 * @author Mike-George Verros
 * @version 1.0
 */
public class ConvertToGreeklish {

    final static Logger logger = Logger.getLogger(ConvertToGreeklish.class);

    public static String greek2Roman(String name) {

        if(logger.isDebugEnabled()){ logger.debug("Convert greek name to greeklish "); }

        return name
                        .replaceAll("α", "a")
                        .replaceAll("ά", "a")
                        .replaceAll("Α", "A")
                        .replaceAll("Ά", "A")
                        .replaceAll("β", "b")
                        .replaceAll("Β", "B")
                        .replaceAll("γ", "g")
                        .replaceAll("Γ", "G")
                        .replaceAll("δ", "d")
                        .replaceAll("Δ", "D")
                        .replaceAll("ε", "e")
                        .replaceAll("έ", "e")
                        .replaceAll("Ε", "e")
                        .replaceAll("Έ", "E")
                        .replaceAll("ζ", "z")
                        .replaceAll("Ζ", "Z")
                        .replaceAll("η", "i")
                        .replaceAll("ή", "i")
                        .replaceAll("Ή", "I")
                        .replaceAll("θ", "th")
                        .replaceAll("Θ", "Th")
                        .replaceAll("ι", "i")
                        .replaceAll("ί", "i")
                        .replaceAll("ϊ", "i")
                        .replaceAll("Ί", "I")
                        .replaceAll("Ι", "I")
                        .replaceAll("κ", "k")
                        .replaceAll("Κ", "K")
                        .replaceAll("λ", "l")
                        .replaceAll("Λ", "L")
                        .replaceAll("μ", "m")
                        .replaceAll("Μ", "M")
                        .replaceAll("ν", "n")
                        .replaceAll("Ν", "N")
                        .replaceAll("ξ", "ks")
                        .replaceAll("Ξ", "Ks")
                        .replaceAll("ο", "o")
                        .replaceAll("ό", "o")
                        .replaceAll("Ό", "O")
                        .replaceAll("Ο", "O")
                        .replaceAll("π", "p")
                        .replaceAll("Π", "P")
                        .replaceAll("ρ", "r")
                        .replaceAll("Ρ", "R")
                        .replaceAll("σ", "s")
                        .replaceAll("Σ", "S")
                        .replaceAll("ς", "s")
                        .replaceAll("τ", "t")
                        .replaceAll("Τ", "T")
                        .replaceAll("υ", "u")
                        .replaceAll("ύ", "u")
                        .replaceAll("Ύ", "Y")
                        .replaceAll("Υ", "Y")
                        .replaceAll("φ", "f")
                        .replaceAll("Φ", "F")
                        .replaceAll("χ", "x")
                        .replaceAll("Χ", "X")
                        .replaceAll("ψ", "ps")
                        .replaceAll("Ψ", "ps")
                        .replaceAll("ω", "w")
                        .replaceAll("ώ", "w")
                        .replaceAll("΄Ώ", "W")
                        .replaceAll("Ω", "W")
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

    }
}
