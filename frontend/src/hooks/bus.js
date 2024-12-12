import { useMutation, useQuery } from "@tanstack/react-query";
import APIClient from "../services/api-client";
import ms from "ms";
import queryClient from "../services/query-client";

const apiClient = new APIClient("/bus")

export const useGetBuss = () => useQuery({
    queryKey: ["bus"],
    queryFn: apiClient.getAll,
    staleTime: ms("24h")
})

export const useGetBus = (id) => useQuery({
    queryKey: ["bus", id],
    queryFn: () => apiClient.get(id),
})

export const useAddBus = () => {
    return useMutation({
        mutationFn: (newBus) => apiClient.post(newBus),
        onSuccess: () => {
            queryClient.invalidateQueries(["bus"]);
        }
    });
};

export const useUpdateBus = () => {
    return useMutation({
        mutationFn: ({ id, updatedBus }) => apiClient.put(id, updatedBus),
        onSuccess: () => {
            queryClient.invalidateQueries(["bus"]);
        }
    });
};

export const useDeleteBus = () => {
    return useMutation({
        mutationFn: (id) => apiClient.delete(id),
        onSuccess: () => {
            queryClient.invalidateQueries(["bus"]);
        }
    });
};