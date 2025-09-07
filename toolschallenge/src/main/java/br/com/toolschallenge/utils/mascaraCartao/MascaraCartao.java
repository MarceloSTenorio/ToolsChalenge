package br.com.toolschallenge.utils.mascaraCartao;

public interface MascaraCartao {
    static String mascararCartao(String cartao) {
        if (cartao == null || cartao.length() < 4) {
            return cartao;
        }
        int length = cartao.length();
        String ultimos4 = cartao.substring(length - 4);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - 4; i++) {
            sb.append("*");
        }
        sb.append(ultimos4);
        return sb.toString();
    }
}
