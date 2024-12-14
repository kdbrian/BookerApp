package io.kdbrian.bookercomposeapp.domain.repo.companies

import src.main.graphql.GetCompaniesQuery
import src.main.graphql.GetCompanyByIdQuery
import src.main.graphql.GetCompanyByNameQuery
import src.main.graphql.SearchForCompaniesWithLocationQuery
import src.main.graphql.SearchForCompaniesWithNameQuery

interface GraphCompaniesRepo {
    suspend fun getCompanies(): Result<GetCompaniesQuery.Data>
    suspend fun getCompanyById(companyId: String): Result<GetCompanyByIdQuery.Data>
    suspend fun getCompanyByName(companyName: String): Result<GetCompanyByNameQuery.Data>
    suspend fun searchCompaniesWithName(companyName: String): Result<SearchForCompaniesWithNameQuery.Data>
    suspend fun searchCompaniesWithLocation(locationName: String): Result<SearchForCompaniesWithLocationQuery.Data>
}