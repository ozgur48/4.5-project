package com.turkcell.product_service.application.command;

import com.turkcell.product_service.application.dto.DeletedProductResponse;
import com.turkcell.product_service.core.cqrs.Command;

public record DeleteProductByIdCommand (
        String id )implements Command<DeletedProductResponse>{}
