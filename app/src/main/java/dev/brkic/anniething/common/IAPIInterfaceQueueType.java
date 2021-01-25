package dev.brkic.anniething.common;

import java.util.List;

import dev.brkic.anniething.models.Border;
import dev.brkic.anniething.models.Queue;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIInterfaceQueueType {
    @GET("queues.json")
    Call<List<Queue>> getQueues();
}
