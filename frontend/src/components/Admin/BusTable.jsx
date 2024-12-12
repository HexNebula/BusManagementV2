import React, { useState, useMemo } from "react";
import {
  Box,
  Button,
  Chip,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  IconButton,
  Stack,
  Typography,
} from "@mui/material";
import {
  MaterialReactTable,
  useMaterialReactTable,
} from "material-react-table";
//import FlightIcon from "flighticon@mui/icons-material/flight";
import VisibilityIcon from "@mui/icons-material/Visibility";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import { useGetBus } from "../../hooks/bus";
import CircularProgress from "@mui/material/CircularProgress";

const BusTable = () => {
  const navigate = useNavigate();
  const [selectedBus, setSelectedBus] = useState(null);
  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);

  const { data: busData, isLoading, isError, error } = useGetBus();

  const handleDeleteClick = (bus) => {
    setSelectedBus(bus);
    setIsDeleteDialogOpen(true);
  };

  const handleConfirmDelete = () => {
    if (selectedBus) {
      // Call API to delete bus here, then update local state
      toast("✅ Successfully deleted");
      setIsDeleteDialogOpen(false);
      setSelectedBus(null);
    }
  };

  const handleCloseDeleteDialog = () => {
    setSelectedBus(null);
    setIsDeleteDialogOpen(false);
  };

  const columns = useMemo(
    () => [
      {
        accessorKey: "id",
        header: "Bus ID",
        size: 10,
        Cell: ({ row }) => (
          <Stack
            direction='column'
            justifyContent='flex-start'
            alignItems='flex-start'
          >
            <Typography variant='h6'>{row.original.id}</Typography>
          </Stack>
        ),
      },
      {
        accessorKey: "busName",
        header: "Bus Name",
        size: 180,
        Cell: ({ cell, row }) => (
          <Stack
            direction='row'
            justifyContent='flex-start'
            alignItems='flex-start'
            spacing={2}
          >
            <FlightIcon />
            <Stack spacing={-1}>
              <Typography variant='h6'>{cell.getValue()}</Typography>
              <Typography variant='body1'>
                {row.original.airlineName}
              </Typography>
            </Stack>
          </Stack>
        ),
      },
      {
        accessorKey: "capacity",
        header: "Capacity",
        size: 50,
        Cell: ({ row }) => (
          <Stack
            direction='column'
            justifyContent='flex-start'
            alignItems='flex-start'
          >
            <Typography variant='h6'>{row.original.capacity}</Typography>
          </Stack>
        ),
      },
      {
        accessorKey: "busType",
        header: "Bus Type",
        size: 200,
        Cell: ({ row }) => (
          <Stack
            direction='column'
            justifyContent='flex-start'
            alignItems='flex-start'
          >
            <Typography variant='h6'>{row.original.busType}</Typography>
          </Stack>
        ),
      },
      {
        accessorKey: "seatType",
        header: "Seat Type",
        size: 200,
        Cell: ({ row }) => (
          <Stack
            direction='column'
            justifyContent='flex-start'
            alignItems='flex-start'
          >
            <Typography variant='h6'>{row.original.seatType}</Typography>
          </Stack>
        ),
      },
      {
        accessorKey: "maximumWeightForPassenger",
        header: "Max: Weight per Person",
        size: 150,
        Cell: ({ row }) => (
          <Stack
            direction='column'
            justifyContent='flex-start'
            alignItems='flex-start'
          >
            <Typography variant='h6'>
              {row.original.maximumWeightForPassenger}
            </Typography>
          </Stack>
        ),
      },
      {
        accessorKey: "airlineService",
        header: "Airline Service",
        size: 200,
        Cell: ({ row }) => (
          <Stack
            direction='column'
            justifyContent='flex-start'
            alignItems='flex-start'
          >
            <Typography variant='h6'>{row.original.airlineService}</Typography>
          </Stack>
        ),
      },
      {
        id: "actions",
        header: "Actions",
        size: 150,
        Cell: ({ row }) => (
          <Box display='flex' flexDirection='row' gap={1}>
            <IconButton color='primary'>
              <VisibilityIcon />
            </IconButton>
            <IconButton
              onClick={() => navigate(`/admin/editBus/${row.original.id}`)}
              color='primary'
            >
              <EditIcon />
            </IconButton>
            <IconButton
              onClick={() => handleDeleteClick(row.original)}
              color='error'
            >
              <DeleteIcon />
            </IconButton>
          </Box>
        ),
      },
    ],
    [navigate]
  );

  if (isLoading) {
    return <CircularProgress />;
  }

  if (isError) {
    return <Typography>Error: {error.message}</Typography>;
  }

  return (
    <Box sx={{ width: "100%", margin: "0 auto" }}>
      <MaterialReactTable
        columns={columns}
        data={busData || []} // Provide default empty array if no data
        pageSizeOptions={[5, 10, 20]}
        defaultPageSize={10}
      />
      <Dialog
        open={isDeleteDialogOpen}
        onClose={handleCloseDeleteDialog}
        aria-labelledby='alert-dialog-title'
        aria-describedby='alert-dialog-description'
      >
        <DialogTitle id='alert-dialog-title'>{"Confirm Deletion"}</DialogTitle>
        <DialogContent>
          <DialogContentText id='alert-dialog-description'>
            Are you sure you want to delete this bus?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDeleteDialog} color='primary'>
            Cancel
          </Button>
          <Button onClick={handleConfirmDelete} color='error' autoFocus>
            Delete
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default BusTable;
