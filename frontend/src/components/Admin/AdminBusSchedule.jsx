import React from "react"
import AdminBusScheduleTable from "./AdminBusScheduleTable"
import { Stack, Typography, Button } from "@mui/material"
import AddIcon from "@mui/icons-material/Add"
import { useNavigate } from "react-router-dom"

const AdminBusSchedule = () => {
  const navigate = useNavigate()

  return (
    <>
      <Stack spacing={2}>
        <Stack
          direction='row'
          justifyContent='space-between'
          alignItems='center'
          spacing={2}
        >
          <Typography variant='h4'>Bus Schedule</Typography>
          <Button
            onClick={() => navigate("/admin/addSchedule")}
            startIcon={<AddIcon />}
            variant='contained'
            color='primary'
          >
            Add New Schedule
          </Button>
        </Stack>
        <AdminBusScheduleTable />
      </Stack>
    </>
  )
}

export default AdminBusSchedule
