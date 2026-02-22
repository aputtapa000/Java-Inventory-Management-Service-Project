package com.aatreya.inventorymgmt.model;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

@Container(containerName = "ShipNode")
public class ShipNode {
    @NotNull
    @Id
    private Long id;
    @NotBlank
    private String name;
    private Boolean isActive;
    private Boolean activeOperation;
    private Boolean ecommActiveOperation;
    @NotBlank
    @PartitionKey // Need to clarify if multiple parties are supported for a given ship node.
    private String type;
    
    public ShipNode() {
    }

    public ShipNode(Long id, String name, Boolean isActive, Boolean activeOperation, Boolean ecommActiveOperation,
            String type) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.activeOperation = activeOperation;
        this.ecommActiveOperation = ecommActiveOperation;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActiveOperation() {
        return activeOperation;
    }

    public void setActiveOperation(boolean activeOperation) {
        this.activeOperation = activeOperation;
    }

    public boolean isEcommActiveOperation() {
        return ecommActiveOperation;
    }

    public void setEcommActiveOperation(boolean ecommActiveOperation) {
        this.ecommActiveOperation = ecommActiveOperation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ShipNode [id=" + id + ", name=" + name + ", isActive=" + isActive + ", activeOperation="
                + activeOperation + ", ecommActiveOperation=" + ecommActiveOperation + ", type=" + type + "]";
    }

    

}
