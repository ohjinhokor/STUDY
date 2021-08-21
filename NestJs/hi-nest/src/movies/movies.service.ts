import { Injectable, NotFoundException } from '@nestjs/common';
import { CreateMovieDto } from './dto/create-movie.dto';
import { UpdateMovieDto } from './dto/update-movie.dto';
import { Movie } from './entities/movie.entity';

@Injectable()
export class MoviesService {

    //private exams : exam[] = [];
    private movies: Movie[] = [];
    //private:
    //    Movie movies[] = [];
    
    getAll(): Movie[]{
        return this.movies;
    }

    getOne(idd:number):Movie{
        const movieee = this.movies.find(movie=>movie.id === idd);
        if(!movieee){
            throw new NotFoundException(`Movie with Id ${idd} not found.`)
        }
        return movieee;
    }

    deleteOne(ids:number){
        this.getOne(ids);
        this.movies = this.movies.filter(movie => movie.id !== ids); //여기서 movie => 의 무비가 Movie형인걸 어떻게 알까? => movies.filter이기 때문에 movies의 요소인 movie형인걸 자동으로 알아냄

        }

    create(movieData: CreateMovieDto){
        this.movies.push({
            id: this.movies.length + 1,
            ...movieData,
        });
    }

    update(idid:number, updateData : UpdateMovieDto){
        const moviemovie = this.getOne(idid);
        this.deleteOne(idid);
        this.movies.push({...moviemovie, ...updateData});
    }
}


