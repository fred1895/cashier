package br.com.fastfood.cashier.rest.dtos;

import java.util.List;

public record OrderRequest(String clientName, List<ProductIdRequest> ids) {
}
