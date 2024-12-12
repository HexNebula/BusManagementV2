import * as React from "react";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import {
  Autocomplete,
  Button,
  Grid,
  MenuItem,
  TextField,
  Typography,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import { useAddBus } from "../../hooks/bus";

const statusOptions = ["On Time", "Delayed", "Cancelled"];
const bus = [
  { code: "LAX", name: "Los Angeles International Bus" },
  { code: "JFK", name: "John F. Kennedy International Bus" },
  { code: "ORD", name: "O'Hare International Bus" },
  { code: "ATL", name: "Hartsfield-Jackson Atlanta International Bus" },
  { code: "DFW", name: "Dallas/Fort Worth International Bus" },
  { code: "DXB", name: "Dubai International Bus" },
  { code: "SIN", name: "Singapore Changi Bus" },
  { code: "LHR", name: "London Heathrow Bus" },
  { code: "CDG", name: "Charles de Gaulle Bus" },
  { code: "HND", name: "Tokyo Haneda Bus" },
];

export default function AddBusSchedule() {
  const [bus, setBus] = React.useState(null);
  const [departureDate, setDepartureDate] = React.useState(null);
  const [departureTime, setDepartureTime] = React.useState(null);
  const [arrivalDate, setArrivalDate] = React.useState(null);
  const [arrivalTime, setArrivalTime] = React.useState(null);
  const [sourceBus, setSourceBus] = React.useState(null);
  const [destinationBus, setDestinationBus] = React.useState(null);
  const [status, setStatus] = React.useState("");

  const mutation = useAddBus();

  const handleAddBusSchedule = () => {
    const busSchedule = {
      busName: bus,
      departureDateTime: `${departureDate?.format(
        "YYYY-MM-DD"
      )}T${departureTime?.format("HH:mm:ss")}`,
      arrivalDateTime: `${arrivalDate?.format(
        "YYYY-MM-DD"
      )}T${arrivalTime?.format("HH:mm:ss")}`,
      source: sourceBus?.name,
      destination: destinationBus?.name,
      status,
    };

    mutation.mutate(busSchedule, {
      onSuccess: () => {
        // Optionally, you can reset the form or show a success message here
        console.log("Bus schedule added successfully");
      },
      onError: (error) => {
        // Handle error case
        console.error("Error adding bus schedule:", error);
      },
    });
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography sx={{ marginBottom: 2 }} variant='h4'>
            Add New Schedule
          </Typography>
          <Autocomplete
            fullWidth
            options={
              ["Bus 101", "Bus 202"] /* Your bus options here */
            }
            getOptionLabel={(option) => option}
            value={bus}
            onChange={(event, newValue) => setBus(newValue)}
            renderInput={(params) => (
              <TextField {...params} label='Select Bus' />
            )}
          />
        </Grid>
        <Grid item xs={6}>
          <DatePicker
            sx={{ width: "100%" }}
            label='Departure Date'
            value={departureDate}
            onChange={(newValue) => setDepartureDate(newValue)}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>
        <Grid item xs={6}>
          <TimePicker
            sx={{ width: "100%" }}
            label='Departure Time'
            value={departureTime}
            onChange={(newValue) => setDepartureTime(newValue)}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>
        <Grid item xs={6}>
          <DatePicker
            sx={{ width: "100%" }}
            label='Arrival Date'
            value={arrivalDate}
            onChange={(newValue) => setArrivalDate(newValue)}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>
        <Grid item xs={6}>
          <TimePicker
            sx={{ width: "100%" }}
            label='Arrival Time'
            value={arrivalTime}
            onChange={(newValue) => setArrivalTime(newValue)}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>
        <Grid item xs={12}>
          <Autocomplete
            fullWidth
            options={bus}
            getOptionLabel={(option) => option.name}
            value={sourceBus}
            onChange={(event, newValue) => setSourceBus(newValue)}
            renderInput={(params) => (
              <TextField {...params} label='Select Source Bus' />
            )}
          />
        </Grid>
        <Grid item xs={12}>
          <Autocomplete
            fullWidth
            options={bus}
            getOptionLabel={(option) => option.name}
            value={destinationBus}
            onChange={(event, newValue) => setDestinationBus(newValue)}
            renderInput={(params) => (
              <TextField {...params} label='Select Destination Bus' />
            )}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            select
            fullWidth
            label='Status'
            value={status}
            onChange={(event) => setStatus(event.target.value)}
          >
            {statusOptions.map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </TextField>
        </Grid>
        <Grid item xs={12}>
          <Button
            variant='contained'
            color='primary'
            onClick={handleAddBusSchedule}
            startIcon={<AddIcon />}
            disabled={mutation.isLoading} // Disable the button while loading
          >
            {mutation.isLoading ? "Adding..." : "Add New Bus Schedule"}
          </Button>
        </Grid>
      </Grid>
    </LocalizationProvider>
  );
}
