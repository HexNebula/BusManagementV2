import * as React from "react"
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs"
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider"
import {
  Button,
  CircularProgress,
  Grid,
  MenuItem,
  TextField,
  Typography,
} from "@mui/material"
import SaveIcon from "@mui/icons-material/Save"
import { useGetBus, useUpdateBus } from "../../hooks/bus"
import toast from "react-hot-toast"
import { useNavigate, useParams } from "react-router-dom"

const busTypeOptions = ["Local", "Commercial", "Seaways", "Private"]
const seatTypeOptions = ["Economy", "Business"]

export default function UpdateBus() {
  const { busId } = useParams()
  const navigate = useNavigate()

  const { data: busData, isLoading } = useGetBus(busId)
  const { mutate: updateBusMutation } = useUpdateBus()

  const [airline, setAirline] = React.useState("")
  const [busName, setBusName] = React.useState("")
  const [capacity, setCapacity] = React.useState("")
  const [busType, setBusType] = React.useState("")
  const [seatType, setSeatType] = React.useState("")
  const [maxWeightForPassenger, setMaxWeightForPassenger] = React.useState("")

  React.useEffect(() => {
    if (busData) {
      console.log("inside use effect")
      setAirline(busData.airlineService)
      setBusName(busData.busName)
      setCapacity(busData.capacity.toString())
      setBusType(busData.busType)
      setSeatType(busData.seatType)
      setMaxWeightForPassenger(busData.maximumWeightForPassenger.toString())
    }
  }, [busData])

  const handleUpdateBus = () => {
    const updatedBus = {
      airlineService: airline,
      busName: busName,
      capacity: parseInt(capacity, 10),
      busType: busType,
      seatType: seatType,
      maximumWeightForPassenger: parseFloat(maxWeightForPassenger),
    }

    updateBusMutation(
      { id: busId, updatedBus },
      {
        onSuccess: () => {
          toast("✅ Bus updated successfully")
          navigate("/admin/manageBus")
        },
        onError: (error) => {
          toast("😵 Error updating bus: " + error.message)
        },
      }
    )
  }

  if (isLoading) return <CircularProgress />

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography sx={{ marginBottom: 2 }} variant='h4'>
            Update Bus
          </Typography>
          <TextField
            fullWidth
            label='Select Airline Service'
            value={airline}
            onChange={(event) => setAirline(event.target.value)}
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
            onClick={handleUpdateBus}
            startIcon={<SaveIcon />}
          >
            Update Bus
          </Button>
        </Grid>
      </Grid>
    </LocalizationProvider>
  )
}
