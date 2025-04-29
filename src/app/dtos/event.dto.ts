// event.dto.ts
export interface EventDTO {
    id: number;
    title: string;
    type: string;
    description: string;
    category: string;
}

// event-with-image.dto.ts
export interface EventWithImageDTO {
    id: number;
    title: string;
    type: string;
    description: string;
    category: string;
    imageBase64: string;
}