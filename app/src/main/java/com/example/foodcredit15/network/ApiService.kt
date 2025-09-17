package com.example.foodcredit15.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*


// ---------------------- API SERVICE ----------------------
interface ApiService {

    // USERS
    @POST("users")
    fun createUser(@Body user: UserRequest): Call<UserResponse>

    @GET("users")
    fun getUsers(): Call<List<UserResponse>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<UserResponse>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: UserRequest): Call<Void>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>

    // EMPLOYEES
    @POST("employees")
    fun createEmployee(@Body employee: EmployeeRequest): Call<EmployeeResponse>

    @GET("employees")
    fun getEmployees(): Call<List<EmployeeResponse>>

    @PUT("employees/{id}")
    fun updateEmployee(@Path("id") id: Int, @Body employee: EmployeeRequest): Call<Void>

    @DELETE("employees/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<Void>

    // DUSTBINS
    @POST("dustbins")
    fun createDustbin(@Body dustbin: DustbinRequest): Call<DustbinResponse>

    @GET("dustbins")
    fun getDustbins(): Call<List<DustbinResponse>>

    @PUT("dustbins/{id}")
    fun updateDustbin(@Path("id") id: Int, @Body dustbin: DustbinRequest): Call<Void>

    @DELETE("dustbins/{id}")
    fun deleteDustbin(@Path("id") id: Int): Call<Void>

    // TRASHLOGS
    @POST("trashlogs")
    fun createTrashLog(@Body log: TrashLogRequest): Call<TrashLogResponse>

    @GET("trashlogs")
    fun getTrashLogs(): Call<List<TrashLogResponse>>

    @DELETE("trashlogs/{id}")
    fun deleteTrashLog(@Path("id") id: Int): Call<Void>

    // VENDING MACHINES
    @POST("vendingmachines")
    fun createMachine(@Body machine: VendingMachineRequest): Call<VendingMachineResponse>

    @GET("vendingmachines")
    fun getMachines(): Call<List<VendingMachineResponse>>

    @PUT("vendingmachines/{id}")
    fun updateMachine(@Path("id") id: Int, @Body machine: VendingMachineRequest): Call<Void>

    @DELETE("vendingmachines/{id}")
    fun deleteMachine(@Path("id") id: Int): Call<Void>

    // ITEMS
    @POST("items")
    fun createItem(@Body item: ItemRequest): Call<ItemResponse>

    @GET("items")
    fun getItems(): Call<List<ItemResponse>>

    @PUT("items/{id}")
    fun updateItem(@Path("id") id: Int, @Body item: ItemRequest): Call<Void>

    @DELETE("items/{id}")
    fun deleteItem(@Path("id") id: Int): Call<Void>

    // REDEMPTIONS
    @POST("redemptions")
    fun createRedemption(@Body redemption: RedemptionRequest): Call<RedemptionResponse>

    @GET("redemptions")
    fun getRedemptions(): Call<List<RedemptionResponse>>

    @DELETE("redemptions/{id}")
    fun deleteRedemption(@Path("id") id: Int): Call<Void>

    // TASKS
    @POST("tasks")
    fun createTask(@Body task: TaskRequest): Call<TaskResponse>

    @GET("tasks")
    fun getTasks(): Call<List<TaskResponse>>

    @PUT("tasks/{id}")
    fun updateTask(@Path("id") id: Int, @Body task: TaskRequest): Call<Void>

    @DELETE("tasks/{id}")
    fun deleteTask(@Path("id") id: Int): Call<Void>

    // REFILLS
    @POST("refills")
    fun createRefill(@Body refill: RefillRequest): Call<RefillResponse>

    @GET("refills")
    fun getRefills(): Call<List<RefillResponse>>

    @PUT("refills/{id}")
    fun updateRefill(@Path("id") id: Int, @Body refill: RefillRequest): Call<Void>

    @DELETE("refills/{id}")
    fun deleteRefill(@Path("id") id: Int): Call<Void>

    @POST("users/login")
    fun loginUser(@Body request: LoginRequest): Call<UserResponse>

}
