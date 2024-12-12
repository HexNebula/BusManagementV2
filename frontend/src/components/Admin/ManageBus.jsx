import React, { useState } from "react"
import AdminBusScheduleTable from "./AdminBusScheduleTable.jsx"
import { Stack, Typography, Button, TextField, Grid } from "@mui/material"
import AddIcon from "@mui/icons-material/Add"
import SearchIcon from "@mui/icons-material/Search"
import { useNavigate } from "react-router-dom"
import BusTable from "./BusTable.jsx"
import { useGetBus } from "../../hooks/bus.js"

const ManageBus = () => {
  const navigate = useNavigate()

  const [busId, setBusId] = useState(null)

  return (
    <>
      <Stack spacing={2}>
        <Stack
          direction='row'
          justifyContent='space-between'
          alignItems='center'
          spacing={2}
        >
          <Typography variant='h4'>Manage Bus</Typography>
          <Button
            onClick={() => navigate("/admin/addBus")}
            startIcon={<AddIcon />}
            variant='contained'
            color='primary'
          >
            Add New Bus
          </Button>
        </Stack>
        <BusTable />
      </Stack>
    </>
  )
}

export default ManageBus
