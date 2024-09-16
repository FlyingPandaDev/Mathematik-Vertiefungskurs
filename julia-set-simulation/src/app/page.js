import Head from "next/head";
import JuliaSet from "../components/JuliaSet";

export default function Home() {
  return (
    <div>
      <Head>
        <title>Julia Set Simulation</title>
        <meta name="description" content="Julia Set Simulation in Next.js" />
      </Head>

      <main>
        <h1>Julia Set Simulation</h1>
        <JuliaSet />
      </main>
    </div>
  );
}
