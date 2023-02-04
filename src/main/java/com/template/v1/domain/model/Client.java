package com.template.v1.domain.model;

import com.template.common.domain.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "client_name", nullable = false, unique = true)
    private String clientName;

    @Column(name = "api_name", nullable = false, unique = true)
    private String apiName;

    @Column(name = "domain", nullable = false, unique = true)
    private String domain;

    @Column(name = "api_url", nullable = false)
    private String apiUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(clientName, client.clientName) && Objects.equals(apiName, client.apiName) && Objects.equals(domain, client.domain) && Objects.equals(apiUrl, client.apiUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, clientName, apiName, domain, apiUrl);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", apiName='" + apiName + '\'' +
                ", domain='" + domain + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                '}';
    }
}
