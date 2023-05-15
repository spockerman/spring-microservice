package br.com.alltracks.storm.inventoryservice.service;

import br.com.alltracks.storm.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isStock(List<String> skuCode);
}
