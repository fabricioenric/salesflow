package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.DashboardDTO;
import com.salesflow.domain.usecases.ObterResumoDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow/admin/dashboard")
public class DashboardAdminController {

    private final ObterResumoDashboard resumo;

    public DashboardAdminController(ObterResumoDashboard r) {
        this.resumo = r;
    }

    @GetMapping
    public DashboardDTO geral() {
        return resumo.execute();
    }
}