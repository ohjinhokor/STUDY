import { Body, Controller, Delete, Get, Param, Patch, PayloadTooLargeException, Post, Query } from '@nestjs/common';
import { create } from 'eslint/lib/rules/*';
import { CreateMovieDto } from './dto/create-movie.dto';
import { UpdateMovieDto } from './dto/update-movie.dto';
import { Movie } from './entities/movie.entity';
import { MoviesService } from './movies.service';

@Controller('movies')
export class MoviesController {
    constructor(private readonly movieService: MoviesService){}

    @Get()
    getAll(): Movie[]{
        return this.movieService.getAll();
    }

    @Get("/search")
    search(@Query('year') searchingYear:string){
        return `we are searching for a movie after : ${searchingYear} `;
    }

    @Get("/:id")
    getOne(@Param('id') movieId: number): Movie{
            //console.log(typeof movieId)
            return this.movieService.getOne(movieId);
        }
    
    @Post()
    create(@Body() movieData: CreateMovieDto){
        return this.movieService.create(movieData)
    }    

    @Delete("/:id")
    deletemovie(@Param("id") id: number){
        return this.movieService.deleteOne(id);
    }    

    @Patch("/:id")
    patch(@Param("id") patchmovieiddd : number, @Body() updateData : UpdateMovieDto){
       return this.movieService.update(patchmovieiddd, updateData);
    }
}


   

