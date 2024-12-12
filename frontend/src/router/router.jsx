import { createBrowserRouter } from "react-router-dom";
import Layout from "../components/Commons/Layout";
import HomePage from "../components/Commons/HomePage";
import AdminBusSchedule from "../components/Admin/AdminBusSchedule";
import ManageAdmins from "../components/Admin/ManageAdmins";
import AddBusSchedule from "../components/Admin/AddBusSchedule";
import AddBus from "../components/Admin/AddBus.jsx";
import UserProfile from "../components/User/UserProfile.jsx";
import ManageBus from "../components/Admin/ManageBus.jsx";
import BusTable from "../components/Admin/BusTable.jsx";
import SelectSeat from "../components/BusReservation/SelectSeat.jsx";
import SelectedSeatInformation from "../components/BusReservation/SelectedSeatInformation.jsx";
import ProceedPayment from "../components/BusReservation/ProceedPayment.jsx";
import PaymentSucess from "../components/BusReservation/PaymentSucess.jsx";
import UpdateBus from "../components/Admin/UpdateBus.jsx";
import BusList from "../components/BusReservation/BusList.jsx";
import AdminHome from "../components/Admin/AdminHome.jsx";
import GeoCoder from "../components/BusReservation/GeoCoder.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    // errorElement: <ErrorPage />,
    children: [
      { index: true, element: <HomePage /> },
      { path: "/admin/home", element: <AdminHome /> },
      { path: "/admin/schedule", element: <AdminBusSchedule /> },
      { path: "/admin/addSchedule", element: <AddBusSchedule /> },
      { path: "/admin/manageAdmins", element: <ManageAdmins /> },
      { path: "/admin/manageBus", element: <ManageBus /> },
      { path: "/admin/addBus", element: <AddBus /> },
      { path: "/admin/editBus/:busId", element: <UpdateBus /> },
      { path: "/admin/busTable", element: <BusTable /> },
      { path: "/reservation/busList", element: <BusList /> },
      { path: "/user/userProfile", element: <UserProfile /> },
      { path: "/reservation/bus-reservation", element: <SelectSeat /> },
      { path: "/reservation/seat-info", element: <SelectedSeatInformation /> },
      { path: "/reservation/payment", element: <ProceedPayment /> },
      { path: "/reservation/payment-success", element: <PaymentSucess /> },
      { path: "/reservation/geo-coder", element: <GeoCoder /> }
    ],
  },
]);

export default router;
