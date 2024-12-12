import * as React from "react"
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs"
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider"
import {
  Autocomplete,
  Button,
  Grid,
  MenuItem,
  TextField,
  Typography,
} from "@mui/material"
import AddIcon from "@mui/icons-material/Add"
import { useAddBus, useGetBus, useUpdateBus } from "../../hooks/bus"
import toast from "react-hot-toast"
import { useNavigate, useParams } from "react-router-dom"

const busTypeOptions = ["Local", "Commercial", "Seaways", "Private"]
const seatTypeOptions = ["Economy", "Business"]

export default function AddBus() {
  const [airline, setAirline] = React.useState(null)
  const [busName, setBusName] = React.useState(null)
  const [capacity, setCapacity] = React.useState(null)
  const [busType, setBusType] = React.useState(null)
  const [seatType, setSeatType] = React.useState(null)
  const [maxWeightForPassenger, setMaxWeightForPassenger] = React.useState(null)
  const navigate = useNavigate()

  const { mutate: addBusMutation } = useAddBus()

  const handleAddBus = () => {
    const newBus = {
      airlineService: airline,
      busName: busName,
      capacity: parseInt(capacity, 10),
      busType: busType,
      seatType: seatType,
      maximumWeightForPassenger: parseFloat(maxWeightForPassenger),
    }

    addBusMutation(newBus, {
      onSuccess: () => {
        toast("✅ Bus added successfully")
        setAirline(null)
        setBusName("")
        setCapacity("")
        setBusType(null)
        setSeatType(null)
        setMaxWeightForPassenger("")
        navigate("/admin/manageBus")
      },
      onError: (error) => {
        toast("😵 Error adding bus: " + error.message)
      },
    })
  }

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography sx={{ marginBottom: 2 }} variant='h4'>
            Add New Bus
          </Typography>
          <Autocomplete
            fullWidth
            options={["ABC", "CDF"] /* Your bus options here */}
            getOptionLabel={(option) => option}
            value={airline}
            onChange={(event, newValue) => setAirline(newValue)}
            renderInput={(params) => (
              <TextField {...params} label='Select Airline Service' />
            )}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            label='Enter Bus Name'
            value={busName}
            onChange={(event) => setBusName(event.target.value)}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            label='Enter Bus Capacity of People'
            value={capacity}
            onChange={(event) => setCapacity(event.target.value)}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            select
            fullWidth
            label='Select Bus Type'
            value={busType}
            onChange={(event) => setBusType(event.target.value)}
          >
            {busTypeOptions.map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </TextField>
        </Grid>
        <Grid item xs={6}>
          <TextField
            select
            fullWidth
            label='Select Seat Type'
            value={seatType}
            onChange={(event) => setSeatType(event.target.value)}
          >
            {seatTypeOptions.map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </TextField>
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            label='Enter Maximum Weight Capacity per Person'
            value={maxWeightForPassenger}
            onChange={(event) => setMaxWeightForPassenger(event.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <Button
            variant='contained'
            color='primary'
            onClick={handleAddBus}
            startIcon={<AddIcon />}
          >
            Add New Bus
          </Button>
        </Grid>
      </Grid>
    </LocalizationProvider>
  )
}
