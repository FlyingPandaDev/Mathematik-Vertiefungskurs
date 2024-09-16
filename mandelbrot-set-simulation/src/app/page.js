import Head from "next/head";
import MandelbrotSet from "../components/MandelbrotSet";

export default function Home() {
  return (
    <div>
      <Head>
        <title>Mandelbrot Set Simulation</title>
        <meta
          name="description"
          content="Mandelbrot Set Simulation in Next.js"
        />
      </Head>

      <main>
        <h1>Mandelbrot Set Simulation</h1>
        <MandelbrotSet />
      </main>
    </div>
  );
}
