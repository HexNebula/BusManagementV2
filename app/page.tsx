import Container from "@/app/components/Container";
import ListingCard from "@/app/components/listings/ListingCard";
import EmptyState from "@/app/components/EmptyState";
<<<<<<< HEAD
import ChatBubble from "@/app/components/ChatBubble";
=======

>>>>>>> 9627621 (Front end with NextJs)
import getListings, {
  IListingsParams
} from "@/app/actions/getListings";
import getCurrentUser from "@/app/actions/getCurrentUser";
import ClientOnly from "./components/ClientOnly";
import Image from "next/image";

<<<<<<< HEAD
// ChatBubble component for the chat feature

=======
>>>>>>> 9627621 (Front end with NextJs)
interface HomeProps {
  searchParams: IListingsParams
};

const Home = async ({ searchParams }: HomeProps) => {
  const listings = await getListings(searchParams);
  const currentUser = await getCurrentUser();

  if (listings.length === 0) {
    return (
      <ClientOnly>
        <EmptyState showReset />
      </ClientOnly>
    );
  }

  return (
    <ClientOnly>
      <Container>
        <div className="container mx-auto px-4 py-16 mt-16">
          <div className="grid md:grid-cols-3 gap-12">
            {/* Feature 1 */}
            <div className="text-center">
              <div className="w-16 h-16 mx-auto mb-6 bg-blue-50 rounded-full flex items-center justify-center">
                <svg className="w-8 h-8 text-blue-600" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M12 8V16M8 12H16" strokeLinecap="round" strokeLinejoin="round" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold mb-3">Your pick of rides at low prices</h3>
              <p className="text-gray-600">No matter where you're going, by bus or carpool, find the perfect ride from our wide range of destinations and routes at low prices.</p>
            </div>

            {/* Feature 2 */}
            <div className="text-center">
              <div className="w-16 h-16 mx-auto mb-6 bg-blue-50 rounded-full flex items-center justify-center">
                <svg className="w-8 h-8 text-blue-600" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M9 12L11 14L15 10" strokeLinecap="round" strokeLinejoin="round" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold mb-3">Trust who you travel with</h3>
              <p className="text-gray-600">We take the time to get to know each of our members and bus partners. We check reviews, profiles and IDs, so you know who you're travelling with.</p>
            </div>

            {/* Feature 3 */}
            <div className="text-center">
              <div className="w-16 h-16 mx-auto mb-6 bg-blue-50 rounded-full flex items-center justify-center">
                <svg className="w-8 h-8 text-blue-600" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M13 10V3L4 14H11V21L20 10H13Z" strokeLinecap="round" strokeLinejoin="round" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold mb-3">Scroll, click, tap and go!</h3>
              <p className="text-gray-600">Booking a ride has never been easier! Thanks to our simple app powered by great technology, you can book a ride close to you in just minutes.</p>
            </div>
          </div>
        </div>

        <div
          className="
            pt-24
            grid 
            grid-cols-1 
            sm:grid-cols-2 
            md:grid-cols-3 
            lg:grid-cols-4
            xl:grid-cols-5
            2xl:grid-cols-6
            gap-8
          "
        >
          {listings.map((listing: any) => (
            <ListingCard
              currentUser={currentUser}
              key={listing.id}
              data={listing}
            />
          ))}
        </div>
<<<<<<< HEAD

=======
>>>>>>> 9627621 (Front end with NextJs)
        <div className="w-full h-screen mt-16">
          <div className="flex flex-row bg-blue-400 justify-center items-center gap-8 px-64">
            <Image src="https://cdn.blablacar.com/kairos/assets/images/scamDetective-653544b71d88f51797db.svg" alt={"Hahahahah"} width={500} height={500} />
            <div className="flex flex-col">

              <div className="font-bold text-2xl text-white">
                Help us keep you safe from scams
              </div>
              <br />
              <div className="text-white font-semibold">At BlaBlaCar, we're working hard to make our platform as secure as it can be. But when scams do happen, we want you to know exactly how to avoid and report them. Follow our tips to help us keep you safe.</div>
              <button className="bg-white text-blue-400 font-bold py-2 mx-auto px-8 mt-4 rounded-full">Learn more</button>
            </div>
          </div>
        </div>
<<<<<<< HEAD

=======
>>>>>>> 9627621 (Front end with NextJs)
        <div className="w-full h-screen">
          <div className="flex flex-row justify-center items-center gap-8 px-64">
            <Image src={"https://cdn.blablacar.com/kairos/assets/images/bus_blablacar-3213ff1065dcd2fce2da.svg"} alt={"Hahahahah"} width={500} height={500} />
            <div className="flex flex-col">
              <div className="font-bold text-2xl text-blue-400">Our buses take you to more than 300 cities for small prices.</div>
              <br />
              <div className="text-gray-800 font-semibold">
                Every week, every month. To meet with a loved one, or to discover a new place. With a big family, or a big luggage. To Paris, Amsterdam or any other European destination.
              </div>
            </div>
          </div>
        </div>
<<<<<<< HEAD
        
      </Container>

      {/* Include the chat bubble */}
      <ChatBubble />
    </ClientOnly>
  );
};
=======
      </Container>
    </ClientOnly>
  )
}
>>>>>>> 9627621 (Front end with NextJs)

export default Home;
