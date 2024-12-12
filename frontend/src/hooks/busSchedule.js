
import { useMutation, useQuery } from "@tanstack/react-query";
import queryClient from "../services/query-client"; // Adjust path as needed
import ms from "ms";
import APIClient from "../services/api-client";

const busScheduleApiClient = new APIClient("/busSchedule")


export const useGetBusSchedules = () => useQuery({
    queryKey: ["busSchedules"],
    queryFn: busScheduleApiClient.getAll,
    staleTime: ms("24h"),
});

export const useGetBusSchedule = (id) => useQuery({
    queryKey: ["busSchedules", id],
    queryFn: () => busScheduleApiClient.get(id),
});

export const useAddBusSchedule = () => {
    return useMutation({
        mutationFn: (newBusSchedule) => busScheduleApiClient.post(newBusSchedule),
        onSuccess: () => {
            queryClient.invalidateQueries(["busSchedules"]);
        },
    });
};

export const useUpdateBusSchedule = () => {
    return useMutation({
        mutationFn: ({ id, updatedBusSchedule }) => busScheduleApiClient.put(id, updatedBusSchedule),
        onSuccess: () => {
            queryClient.invalidateQueries(["busSchedules"]);
        },
    });
};

export const useDeleteBusSchedule = () => {
    return useMutation({
        mutationFn: (id) => busScheduleApiClient.delete(id),
        onSuccess: () => {
            queryClient.invalidateQueries(["busSchedules"]);
        },
    });
};
