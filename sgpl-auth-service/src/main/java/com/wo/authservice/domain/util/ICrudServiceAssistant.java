package com.wo.authservice.domain.util;

import java.util.List;
import java.util.Optional;

public interface ICrudServiceAssistant<DetailsResponse, ActionRequest, ActionResponse, Id> {

    List<DetailsResponse> findAll();

    Optional<DetailsResponse> findById(Id id);

    ActionResponse save(ActionRequest entity);

    ActionResponse deleteById(Id id);

}
