package com.example.foodcredit15.network

import retrofit2.http.*

interface ApiService {

    // ---------------- Users ----------------
    @POST("users")
    suspend fun createUser(@Body user: UserRequest): UserResponse

    @POST("users/login")
    suspend fun loginUser(@Body request: LoginRequest): UserResponse

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponse

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UserRequest)

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)

    // ---------------- Employees ----------------
    @POST("employees")
    suspend fun createEmployee(@Body employee: EmployeeRequest): EmployeeResponse

    @GET("employees")
    suspend fun getEmployees(): List<EmployeeResponse>

    @PUT("employees/{id}")
    suspend fun updateEmployee(@Path("id") id: Int, @Body employee: EmployeeRequest)

    @DELETE("employees/{id}")
    suspend fun deleteEmployee(@Path("id") id: Int)

    // ---------------- Dustbins ----------------
    @POST("dustbins")
    suspend fun createDustbin(@Body dustbin: DustbinRequest): DustbinResponse

    @GET("dustbins")
    suspend fun getDustbins(): List<DustbinResponse>

    @PUT("dustbins/{id}")
    suspend fun updateDustbin(@Path("id") id: Int, @Body dustbin: DustbinRequest)

    @DELETE("dustbins/{id}")
    suspend fun deleteDustbin(@Path("id") id: Int)

    // ---------------- TrashLogs ----------------
    @POST("trashlogs")
    suspend fun createTrashLog(@Body log: TrashLogRequest): TrashLogResponse

    @GET("trashlogs")
    suspend fun getTrashLogs(): List<TrashLogResponse>

    @DELETE("trashlogs/{id}")
    suspend fun deleteTrashLog(@Path("id") id: Int)

    // ---------------- VendingMachines ----------------
    @POST("vendingmachines")
    suspend fun createMachine(@Body machine: VendingMachineRequest): VendingMachineResponse

    @GET("vendingmachines")
    suspend fun getMachines(): List<VendingMachineResponse>

    @PUT("vendingmachines/{id}")
    suspend fun updateMachine(@Path("id") id: Int, @Body machine: VendingMachineRequest)

    @DELETE("vendingmachines/{id}")
    suspend fun deleteMachine(@Path("id") id: Int)

    // ---------------- Items ----------------
    @POST("items")
    suspend fun createItem(@Body item: ItemRequest): ItemResponse

    @GET("items")
    suspend fun getItems(): List<ItemResponse>

    @PUT("items/{id}")
    suspend fun updateItem(@Path("id") id: Int, @Body item: ItemRequest)

    @DELETE("items/{id}")
    suspend fun deleteItem(@Path("id") id: Int)

    // ---------------- Redemptions ----------------
    @POST("redemptions")
    suspend fun createRedemption(@Body redemption: RedemptionRequest): RedemptionResponse

    @GET("redemptions")
    suspend fun getRedemptions(): List<RedemptionResponse>

    @DELETE("redemptions/{id}")
    suspend fun deleteRedemption(@Path("id") id: Int)

    // ---------------- Tasks ----------------
    @POST("tasks")
    suspend fun createTask(@Body task: TaskRequest): TaskResponse

    @GET("tasks")
    suspend fun getTasks(): List<TaskResponse>

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: Int, @Body task: TaskRequest)

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int)

    // ---------------- Refills ----------------
    @POST("refills")
    suspend fun createRefill(@Body refill: RefillRequest): RefillResponse

    @GET("refills")
    suspend fun getRefills(): List<RefillResponse>

    @PUT("refills/{id}")
    suspend fun updateRefill(@Path("id") id: Int, @Body refill: RefillRequest)

    @DELETE("refills/{id}")
    suspend fun deleteRefill(@Path("id") id: Int)


}
